package com.employee.demo.service;

import org.springframework.http.ResponseEntity;

import com.employee.demo.model.Employee;
import com.employee.demo.request.ForgetPasswordRequest;
import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.ResetPasswordRequest;

public interface AuthService {
	 String register(Employee employee);
	 ResponseEntity<?> login(JwtRequest request);
	ResponseEntity<String> forgotPasswordEmployee(ForgetPasswordRequest forgetPasswordRequest);
	ResponseEntity<String> resetPasswordEmployee(ResetPasswordRequest resetPasswordRequest);
	}
