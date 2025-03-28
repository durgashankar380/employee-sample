package com.employee.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.request.ForgetPasswordRequest;
import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.ResetPasswordRequest;
import com.employee.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Employee employee) {
		String msg = authService.register(employee);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
     return authService.login(request);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPasswordEmployee(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
		return authService.forgotPasswordEmployee(forgetPasswordRequest);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPasswordEmployee(@RequestBody ResetPasswordRequest resetPasswordRequest) {
		return authService.resetPasswordEmployee(resetPasswordRequest);
	}
}