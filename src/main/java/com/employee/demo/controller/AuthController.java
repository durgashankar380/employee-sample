package com.employee.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.helper.JwtTokenHelper;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.ForgetPasswordRequest;
import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.ResetPasswordRequest;
import com.employee.demo.response.JwtResponse;
import com.employee.demo.service.EmployeeJwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private EmployeeJwtService employeeJwtService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody EmployeeRequest request) {
		String msg = employeeJwtService.register(request);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> createToken(@RequestBody JwtRequest request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailId());
			String token = jwtTokenHelper.generateToken(userDetails.getUsername());
			return ResponseEntity.ok(new JwtResponse(token));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
	}
	
	@PostMapping("/forget-password")
	public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest request) {
		String msg = employeeJwtService.forgetPassword(request);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
		String msg = employeeJwtService.resetPassword(request);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	

}
