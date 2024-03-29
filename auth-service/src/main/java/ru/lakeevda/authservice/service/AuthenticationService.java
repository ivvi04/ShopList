package ru.lakeevda.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.authservice.config.UserPhonePasswordAuthenticationToken;
import ru.lakeevda.authservice.dto.*;
import ru.lakeevda.authservice.entity.User;
import ru.lakeevda.authservice.entity.enums.UserRole;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private JwtAuthenticationResponse generateToken(Integer phone, String password) {
        UserDetails userDetails = userService
                .loadUserByPhone(phone);

        String jwt = jwtService.generateToken(userDetails);
        return new JwtAuthenticationResponse(userDetails.getUsername(), jwt);
    }

    @Transactional
    public User createUser(SignUpRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(UserRole.USER)
                .build();

        return userService.saveUser(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UserPhonePasswordAuthenticationToken(
                request.getPhone(),
                request.getPassword()
        ));
        return generateToken(request.getPhone(), request.getPassword());
    }

    public boolean validateToken(ValidTokenRequest request) {
        UserDetails userDetails = userService
                .loadUserByUsername(request.getUsername());

        return jwtService.isTokenValid(request.getToken(), userDetails);
    }

    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }
}
