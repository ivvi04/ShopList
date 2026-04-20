package ru.lakeevda.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.authservice.config.UserPhonePasswordAuthenticationToken;
import ru.lakeevda.authservice.dto.*;
import ru.lakeevda.authservice.entity.User;
import ru.lakeevda.authservice.entity.enums.UserRole;
import ru.lakeevda.authservice.exception.ConfirmPasswordIncorrectException;
import ru.lakeevda.authservice.exception.OldPasswordIncorrectException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private JwtAuthenticationResponse generateToken(long phone) {
        User user = userService.loadUserByPhone(phone);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(user.getUsername(), jwt);
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

        return userService.create(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UserPhonePasswordAuthenticationToken(
                request.getPhone(),
                request.getPassword()
        ));
        return generateToken(request.getPhone());
    }

    public boolean validateToken(ValidTokenRequest request) {
        User user = userService
                .loadUserByPhone(request.getPhone());

        return jwtService.isTokenValid(request.getToken(), user);
    }

    @Transactional
    public JwtAuthenticationResponse changePassword(ChangePasswordRequest request) {
        User user = userService
                .loadUserByPhone(request.getPhone());
        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            throw new OldPasswordIncorrectException("Старый пароль некорректный!");
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new ConfirmPasswordIncorrectException("Новый пароль не совпадает с подтвержденным!");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.create(user, false);
        authenticationManager.authenticate(new UserPhonePasswordAuthenticationToken(
                request.getPhone(),
                request.getPassword()
        ));
        return generateToken(request.getPhone());
    }

    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }
}
