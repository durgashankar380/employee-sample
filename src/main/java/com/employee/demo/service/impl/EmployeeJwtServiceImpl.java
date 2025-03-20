package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeJwtRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.ForgetPasswordRequest;
import com.employee.demo.request.ResetPasswordRequest;
import com.employee.demo.service.EmployeeJwtService;

@Service
public class EmployeeJwtServiceImpl implements EmployeeJwtService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeJwtRepository jwtRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String register(EmployeeRequest request) {
		Optional<Employee> employee = jwtRepository.findByEmailId(request.getEmailId());
		if (employee.isPresent()) {
			return "User already exist with this Email Id.";
		}
		Employee emp = new Employee();
		emp.setName(request.getName());
		emp.setSalary(request.getSalary());
		emp.setDepartment(request.getDepartment());
		emp.setStatus(1);
		emp.setEmailId(request.getEmailId());
		emp.setPassword(passwordEncoder.encode(request.getPassword()));
		jwtRepository.save(emp);
		return "User register succesfully.";
	}

	@Override
	public String forgetPassword(ForgetPasswordRequest request) {
		Employee employee = employeeRepository.findByEmailId(request.getEmailId()).orElse(null);
		if (employee != null) {
			if(request.getNewPassword().equals(request.getConfirmNewPassword())) {
				employee.setPassword(passwordEncoder.encode(request.getConfirmNewPassword()));
				employeeRepository.save(employee);
				return "password reset succesfully.";
			}else {
				return "Both password is not same, please try again. !!";
			}	
		}else {
			return "User don't exist with this Email.";
		}	
	}

	@Override
	public String resetPassword(ResetPasswordRequest request) {
		Employee employee = employeeRepository.findByEmailId(request.getEmailId()).orElse(null);
		if (employee != null) {
			if(passwordEncoder.matches(request.getCurrentPassword(), employee.getPassword())) {
				if(request.getNewPassword().equals(request.getConfirmNewPassword())) {
					employee.setPassword(passwordEncoder.encode(request.getConfirmNewPassword()));
					employeeRepository.save(employee);
					return "password reset succesfully.";
				}else {
					return "Your new password is not same.";
				}
			}else {
				return "Invalid Password. !!";
			}
			
		}else {
			return "User don't exist with this email id.";
		}	
		
	
	}

}
