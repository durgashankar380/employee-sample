package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeJwtRepository;
import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.response.JwtResponse;
import com.employee.demo.security.EmployeeUserDetailsService;
import com.employee.demo.security.JwtUtils;
import com.employee.demo.service.JwtService;

@Service
public class JwtImpl implements JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private EmployeeUserDetailsService userDetailsService;

    @Autowired
    private EmployeeJwtRepository employeeJwtRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<JwtResponse> login(JwtRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ResponseEntity<String> register(RequestEmployee employee) {
        System.out.println("Incoming Employee Data: " + employee.toString());

        Optional<Employee> existingEmployee = employeeJwtRepository.findByEmail(employee.getEmail());
        if (existingEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists!");
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeJwtRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body("Employee registered successfully!");
    }
}
