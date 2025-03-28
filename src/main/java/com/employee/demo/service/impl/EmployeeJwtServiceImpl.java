package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeJwtRepository;
import com.employee.demo.repository.EmployeeRepository;
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

	@Autowired
	private DepartmentRepository departmentRepository;

// Method for add employee.
	@Override
	public String register(Employee employee) {
		// checking employee is exist or not with this emailId.
		Optional<Employee> emp = jwtRepository.findByEmailId(employee.getEmailId());

		// if employee is already exist.
		if (emp.isPresent() && emp.get() != null) {
			return "User already exist with this Email Id.";
		} else {
			// otherwise,
			// now check the department is exist or not.
			Optional<Department> department = departmentRepository.findByDepartmentName(employee.getDepartment());
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
				jwtRepository.save(newEmp);
			} else {
				// otherwise,
				// creating new department and register employee in it.
				Department newDepartment = new Department();
				newDepartment.setDepartmentName(employee.getDepartment());
				newDepartment.setLocation(employee.getDepartments().getLocation());
				newDepartment.setDescription(employee.getDepartments().getDescription());
				newDepartment.setStatus(1);
				departmentRepository.save(newDepartment);
				// now register new employee in database.
				newEmp.setName(employee.getName());
				newEmp.setDepartment(employee.getDepartment());
				newEmp.setSalary(employee.getSalary());
				newEmp.setStatus(1);
				newEmp.setEmailId(employee.getEmailId());
				newEmp.setPassword(passwordEncoder.encode(employee.getPassword()));
				newEmp.setDepartments(newDepartment);
				jwtRepository.save(newEmp);
			}
			return "New User register succesfully.";
		}
	}

// Method for FORGET Password.
	@Override
	public String forgetPassword(ForgetPasswordRequest request) {
		Employee employee = employeeRepository.findByEmailId(request.getEmailId()).orElse(null);
		if (employee != null) {
			if (request.getNewPassword().equals(request.getConfirmNewPassword())) {
				employee.setPassword(passwordEncoder.encode(request.getConfirmNewPassword()));
				employeeRepository.save(employee);
				return "password reset succesfully.";
			} else {
				return "Both password is not same, please try again. !!";
			}
		} else {
			return "User don't exist with this Email.";
		}
	}

// Method for RESET Password.
	@Override
	public String resetPassword(ResetPasswordRequest request) {
		Employee employee = employeeRepository.findByEmailId(request.getEmailId()).orElse(null);
		if (employee != null) {
			if (passwordEncoder.matches(request.getCurrentPassword(), employee.getPassword())) {
				if (request.getNewPassword().equals(request.getConfirmNewPassword())) {
					employee.setPassword(passwordEncoder.encode(request.getConfirmNewPassword()));
					employeeRepository.save(employee);
					return "password reset succesfully.";
				} else {
					return "Your new password is not same.";
				}
			} else {
				return "Invalid Password. !!";
			}
		} else {
			return "User don't exist with this email id.";
		}
	}

}
