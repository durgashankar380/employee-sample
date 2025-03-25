package com.employee.demo.controller;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.JwtForgetRequest;
import com.employee.demo.request.JwtRequest;
import com.employee.demo.response.JwtResponse;
import com.employee.demo.security.JwtHelper;
import com.employee.demo.service.DepartmentService;
import com.employee.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
private DepartmentService departmentService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;
    @Autowired
    private EmployeeService service;
    @Setter
    @Getter
    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            service.setLastLogin(email);
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create_user")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeRequest employeeRequest){
        return service.registerEmployee(employeeRequest);
    }

    @PostMapping("/forget_password")
    public ResponseEntity<?> forgetPassword(@RequestBody JwtForgetRequest request){
       return service.forgetPassword(request);
    }

    @PostMapping("/create_department")
    public Department createDepartment(@RequestBody DepartmentRequest department) {
        return departmentService.saveDepartment(department);
    }
}
