package com.employee.demo.security;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeJwtRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeJwtRepository employeeJwtRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeUserDetailsService(EmployeeJwtRepository employeeJwtRepository, PasswordEncoder passwordEncoder) {
        this.employeeJwtRepository = employeeJwtRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return employeeJwtRepository.findByEmail(email)
                .map(employee -> new User(employee.getEmail(), employee.getPassword(), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + email));
    }

    public Employee saveEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword())); // Encode password before saving
        return employeeJwtRepository.save(employee);
    }
}
