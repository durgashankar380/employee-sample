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
import org.springframework.web.bind.annotation.RequestBody;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeJwtRepository;
import com.employee.demo.request.JwtRequest;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.request.ResetPasswordRequest;
import com.employee.demo.request.ForgotPasswordRequest;
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
    public ResponseEntity<String> register(RequestEmployee employeeRequest) {
        System.out.println("Incoming Employee Data: " + employeeRequest.toString());

        Optional<Employee> existingEmployee = employeeJwtRepository.findByEmail(employeeRequest.getEmail());
        if (existingEmployee.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists!");
        }

        // Convert RequestEmployee to Employee entity
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        
        // Encrypt the password before saving
        String encryptedPassword = passwordEncoder.encode(employeeRequest.getPassword());
        employee.setPassword(encryptedPassword);
        
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());

        employeeJwtRepository.save(employee); // Save encrypted password in DB

        return ResponseEntity.status(HttpStatus.CREATED).body("Employee registered successfully!");
    }
    
    @Override
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
      
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("New password and confirm password must be the same.");
        }


        Optional<Employee> employeeOptional = employeeJwtRepository.findByEmail(request.getEmail());
       if (employeeOptional.isEmpty()  ) {
            return ResponseEntity.badRequest().body("Employee not found.");
        }

        
        Employee employee = employeeOptional.get();
        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
        employeeJwtRepository.save(employee);

        return ResponseEntity.ok("Password set successfully.");
    }
    
    @Override
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
      
       
        Optional<Employee> employeeOptional = employeeJwtRepository.findByEmail(request.getEmail());
  
        if (employeeOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Employee not found.");
        }
        else {
        	 boolean matchPassword = passwordEncoder.matches( request.getCurrentPassword(), employeeOptional.get().getPassword());
        	 if(matchPassword == true) {
        		 if (request.getNewPassword().equals(request.getConfirmPassword())) {
        			 Employee employee = employeeOptional.get();  
        		        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
        		        employeeJwtRepository.save(employee);

        		        return ResponseEntity.ok("Password reset successfully.");
               }else {
            	   return ResponseEntity.badRequest().body("New password and confirm password must be the same.");
               }
        		 
        	 }else {
        		 return ResponseEntity.ok("invalid password");
        	 }      
        }   
    }
}
