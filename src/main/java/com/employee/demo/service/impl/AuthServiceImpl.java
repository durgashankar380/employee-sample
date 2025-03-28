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
import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
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

    @Autowired
	private DepartmentRepository repository;
    
 // Method for add employee.
 	@Override
 	public String register(Employee employee) {
 		// checking employee is exist or not with this emailId.
 		Optional<Employee> emp = employeeRepository.findByEmailId(employee.getEmailId());
  
 		// if employee is already exist.
 		if (emp.isPresent() && emp.get() != null) {
 			return "User already exist with this Email Id.";
 		} else {
 			// otherwise,
 			// now check the department is exist or not.
 			Optional<Department> department = repository.findByName(employee.getDepartment());
 			Employee newEmp = new Employee();
  
 			// if department is already exist, then register employee in existing.
 			// department.
 			if (department.isPresent() && department.get() != null) {
 				newEmp.setName(employee.getName());
 				newEmp.setDepartment(employee.getDepartment());
 				newEmp.setSalary(employee.getSalary());
 				newEmp.setStatus(1);
 				newEmp.setEmailId(employee.getEmailId());
 				newEmp.setPassword(passwordEncoder.encode(employee.getPassword()));
 				newEmp.setDepartments(department.get());
 				employeeRepository.save(newEmp);
 			} else {
 				// otherwise,
 				// creating new department and register employee in it.
 				Department newDepartment = new Department();
 				newDepartment.setName(employee.getDepartment());
 				newDepartment.setLocation(employee.getDepartments().getLocation());
 				newDepartment.setDescription(employee.getDepartments().getDescription());
 				repository.save(newDepartment);
 				// now register new employee in database.
 				newEmp.setName(employee.getName());
 				newEmp.setDepartment(employee.getDepartment());
 				newEmp.setSalary(employee.getSalary());
 				newEmp.setStatus(1);
 				newEmp.setEmailId(employee.getEmailId());
 				newEmp.setPassword(passwordEncoder.encode(employee.getPassword()));
 				newEmp.setDepartments(newDepartment);
 				employeeRepository.save(newEmp);
 			}
 			return "New User register succesfully.";
 		}
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
		Employee employee = employeeRepository.findByEmailId(forgetPasswordRequest.getEmailId()).orElse(null);
		if(employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with this email does not exist");
		}
		if(!forgetPasswordRequest.getPassword().equals(forgetPasswordRequest.getConfirmPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both Password do not match");
		}
		employee.setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
		employeeRepository.save(employee);
		return ResponseEntity.ok("Password set successfully");
	}

	@Override
	public ResponseEntity<String> resetPasswordEmployee(ResetPasswordRequest resetPasswordRequest) {
		Employee employee = employeeRepository.findByEmailId(resetPasswordRequest.getEmailId()).orElse(null);
		if(employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with this email does not exist");
		}
		if(!passwordEncoder.matches(resetPasswordRequest.getCurrentPassword(), employee.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Current Password is incorrect");
		}
		if(!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both password does not match");
		}
		employee.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
		employeeRepository.save(employee);
		return ResponseEntity.ok("Password reset successfully");
	}
}
