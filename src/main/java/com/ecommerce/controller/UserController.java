package com.ecommerce.controller;


import com.ecommerce.dto.UserDTO;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public User registerUser( @Valid @RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }
}

