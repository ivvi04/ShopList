package ru.lakeevda.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.userservice.entity.User;
import ru.lakeevda.userservice.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<User> getUser(@RequestParam Integer phone) {
        User user = userService.getUser(phone);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
}
