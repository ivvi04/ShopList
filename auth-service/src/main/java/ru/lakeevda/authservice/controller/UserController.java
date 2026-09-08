package ru.lakeevda.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.authservice.dto.UserRequest;
import ru.lakeevda.authservice.dto.UserResponse;
import ru.lakeevda.authservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @Operation(summary = "Получение пользователя по номеру телефона")
    @GetMapping("/{phone}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String phone) {
        UserResponse user = service.getUserByPhone(phone);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Получение пользователей, у которых номер телефона начинается на переданное значение")
    @GetMapping("/{phone}/users")
    public ResponseEntity<List<UserResponse>> getUsersByUserPhone(@PathVariable String phone) {
        List<UserResponse> userList = service.getUsersByPhoneStartsWith(phone);
        return ResponseEntity.ok().body(userList);
    }

    @Operation(summary = "Обновление информации пользователя")
    @PutMapping("/update-info")
    public ResponseEntity<UserResponse> updateInfo(@RequestBody UserRequest user) {
        UserResponse updateUser = service.updateInfo(user);
        return ResponseEntity.ok().body(updateUser);
    }
}
