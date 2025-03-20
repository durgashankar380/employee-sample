package com.employee.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.request.ResetPasswordRequest;
import com.employee.demo.request.ForgotPasswordRequest;
import com.employee.demo.response.JwtResponse;
import com.employee.demo.service.JwtService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        return jwtService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RequestEmployee employee) {
        return jwtService.register(employee);
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return jwtService.forgotPassword(request);
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        return jwtService.resetPassword(request);
    }
    
}
