package ru.lakeevda.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.authservice.entity.User;
import ru.lakeevda.authservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{phone}")
    public ResponseEntity<User> getUser(@PathVariable long phone) {
        User user = userService.getUserByPhone(phone);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{phone}/users")
    public ResponseEntity<List<User>> getUsersByUserPhone(@PathVariable String phone) {
        List<User> userList = userService.getUsersByPhoneStartsWith(phone);
        return ResponseEntity.ok().body(userList);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.update(user);
        return ResponseEntity.ok().body(updateUser);
    }
}
