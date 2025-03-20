package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.demo.helper.JWTHelper;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.ForgetPasswordRequest;
import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.ResetPasswordRequest;
import com.employee.demo.response.JwtResponse;
import com.employee.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public ResponseEntity<String> register(Employee employee) {
		if(employeeRepository.findByEmailId(employee.getEmailId()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address already exists.");
		}
		 employee.setPassword(passwordEncoder.encode(employee.getPassword()));
	        employeeRepository.save(employee);
		return ResponseEntity.ok("User registered successsfully.");
		
    }

	@Override
	public ResponseEntity<?> login(JwtRequest request) {
		try {
		 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailId());
	        String token = jwtHelper.generateToken(userDetails.getUsername());
	        return ResponseEntity.ok(new JwtResponse(token));
	}catch(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}	
	}

	@Override
	public ResponseEntity<String> forgotPasswordEmployee(ForgetPasswordRequest forgetPasswordRequest) {
		Optional<Employee> employeeOptional = employeeRepository.findByEmailId(forgetPasswordRequest.getEmailId());
		if(employeeOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with this email does not exist");
		}
		
		Employee employee = employeeOptional.get();
		
		if(forgetPasswordRequest.getPassword().equals(forgetPasswordRequest.getConfirmPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both Password do not match");
		}
		
		String encode = passwordEncoder.encode(forgetPasswordRequest.getPassword());
		
		employee.setPassword(encode);
		employeeRepository.save(employee);
		
		return ResponseEntity.ok("Password set successfully");
	}

	
}
