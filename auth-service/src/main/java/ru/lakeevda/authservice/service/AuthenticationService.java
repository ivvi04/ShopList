package ru.lakeevda.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lakeevda.authservice.authentication.UserPhonePasswordAuthenticationToken;
import ru.lakeevda.authservice.dto.*;
import ru.lakeevda.authservice.entity.UserEntity;
import ru.lakeevda.authservice.entity.enums.UserRole;
import ru.lakeevda.authservice.exception.ConfirmPasswordIncorrectException;
import ru.lakeevda.authservice.exception.OldPasswordIncorrectException;
import ru.lakeevda.authservice.exception.UserEmailExistException;
import ru.lakeevda.authservice.exception.UserPhoneExistException;
import ru.lakeevda.authservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final UserRepository userRepository;

    private JwtAuthenticationResponse generateToken(String phone) {
        UserEntity user = userService.findUserByPhone(phone);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(user.getPhone(), jwt);
    }

    @Transactional
    public void signUp(SignUpRequest request) {
        UserEntity user = new UserEntity(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getPhone(),
                request.getEmail(),
                UserRole.USER.toString());

        create(user, true);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UserPhonePasswordAuthenticationToken(
                request.getPhone(),
                request.getPassword()
        ));
        return generateToken(request.getPhone());
    }

    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }

    @Transactional
    public JwtAuthenticationResponse changePassword(ChangePasswordRequest request) {
        UserEntity user = userService.findUserByPhone(request.getPhone());
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            throw new OldPasswordIncorrectException("Старый пароль некорректный!");
        if (!request.getPassword().equals(request.getConfirmPassword()))
            throw new ConfirmPasswordIncorrectException("Новый пароль не совпадает с подтвержденным!");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        create(user, false);
        authenticationManager.authenticate(new UserPhonePasswordAuthenticationToken(
                request.getPhone(),
                request.getPassword()
        ));
        return generateToken(request.getPhone());
    }

    private void create(UserEntity user, boolean checked) {
        if (checked) {
            if (userRepository.existsByPhone(user.getPhone()))
                throw new UserPhoneExistException("Пользователь с таким телефоном уже существует!");
            if (userRepository.existsByEmail(user.getEmail()))
                throw new UserEmailExistException("Пользователь с таким email уже существует!");
            if (user.getUsername().isEmpty()) user.setUsername("user" + user.getId());
            user.setRole(UserRole.USER.toString());
        }
        userRepository.save(user);
    }
}
