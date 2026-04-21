package ru.lakeevda.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.authservice.dto.ChangePasswordRequest;
import ru.lakeevda.authservice.dto.JwtAuthenticationResponse;
import ru.lakeevda.authservice.dto.SignInRequest;
import ru.lakeevda.authservice.dto.SignUpRequest;
import ru.lakeevda.authservice.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest request) {
        authenticationService.createUser(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody @Valid SignInRequest request) {
        return ResponseEntity.ok().body(authenticationService.signIn(request));
    }

    @Operation(summary = "Смена пароля")
    @PostMapping("/change-password")
    public ResponseEntity<JwtAuthenticationResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok().body(authenticationService.changePassword(changePasswordRequest));
    }

    @Operation(summary = "Проверка токена")
    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        if (authenticationService.validateToken(token)) return "Токен валидный";
        else return "Токен не валидный";
    }
}
