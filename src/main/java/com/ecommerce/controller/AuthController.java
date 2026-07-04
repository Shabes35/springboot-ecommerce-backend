package com.ecommerce.controller;


import com.ecommerce.dto.LoginRequestDTO;
import com.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequest){
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
