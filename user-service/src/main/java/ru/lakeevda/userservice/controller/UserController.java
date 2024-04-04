package ru.lakeevda.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.userservice.entity.User;
import ru.lakeevda.userservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{phone}")
    public ResponseEntity<User> getUser(@PathVariable Integer phone) {
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
        User updateUser = userService.updateUser(user);
        return ResponseEntity.ok().body(updateUser);
    }
}
