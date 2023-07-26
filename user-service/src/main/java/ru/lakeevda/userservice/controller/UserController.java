package ru.lakeevda.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.userservice.entity.UserData;
import ru.lakeevda.userservice.service.UserService;

import java.util.List;

@CrossOrigin(allowCredentials = "false")
@RestController
@Tag(name = "Users", description = "All methods for Users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @Operation(summary = "Get all Users")
    public List<UserData> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get User by ID")
    public UserData getData(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PutMapping("/user")
    @Operation(summary = "Save/Update User")
    public void saveData(@RequestBody UserData userData) {
        userService.saveUser(userData);
    }

    @Operation(summary = "Get name user by phone")
    @GetMapping("/phone/{phone}")
    public String getNameByPhone(@PathVariable Integer phone) {
        return userService.getNameByPhone(phone);
    }

}
