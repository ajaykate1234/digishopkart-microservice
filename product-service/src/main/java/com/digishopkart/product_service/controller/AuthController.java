package com.digishopkart.product_service.controller;

import com.digishopkart.product_service.model.LoginRequest;
import com.digishopkart.product_service.util.JWTUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private JWTUtil jwtUtil;

    public AuthController(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        return jwtUtil.generateToken(username);
    }
}


