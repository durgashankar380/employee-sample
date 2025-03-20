package com.employee.demo.service;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;

import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.request.ResetPasswordRequest;
import com.employee.demo.request.ForgotPasswordRequest;
import com.employee.demo.response.JwtResponse;

public interface JwtService {
	
	ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request);

	ResponseEntity<String> register(@RequestBody RequestEmployee employee);

	ResponseEntity<String> forgotPassword(ForgotPasswordRequest request);
	
	ResponseEntity<String> resetPassword(ResetPasswordRequest request);
}
