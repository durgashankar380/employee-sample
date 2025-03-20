package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.demo.ApiStatus.ApiStatus;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepositoryPagination;
import com.employee.demo.request.EmployeePageRequestDto;
import com.employee.demo.service.EmployeeServicePagination;

@Service
public class EmployeeServiceImplPagination implements EmployeeServicePagination {

		private final EmployeeRepositoryPagination employeeRepositoryPagination;
		private final PasswordEncoder passwordEncoder;
	
	public EmployeeServiceImplPagination(EmployeeRepositoryPagination employeeRepositoryPagination,PasswordEncoder passwordEncoder) {
		this.employeeRepositoryPagination = employeeRepositoryPagination;
		this.passwordEncoder=passwordEncoder;
	}

	// retrieve all employees using pagination
	
	
	@Override
	public Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto) {
		return employeeRepositoryPagination.findAll(dto.getPageable());
	}

	// SEARCH EMPLOYEE USING ID , NAME , SALARY , DEPARTMENT.
	
	@Override
	public Page<Employee> searchEmployees(EmployeePageRequestDto dto) {
		Pageable pageable = dto.getPageable();
		return employeeRepositoryPagination.searchEmployees(
				dto.getSearchKeyword(),
				dto.getId(),
				dto.getSalary(),
				dto.getDepartment(),
				pageable
				);
	}	

	// retrieves list of employees using pagination and sorting
	
	@Override
	public Page<Employee> getAllEmployeeUsingPaginationSort(EmployeePageRequestDto dto) {
		return employeeRepositoryPagination.findAll(dto.getPageable());
	}

	
	
	@Override
	public Page<Employee> getEmployeeByStatus(EmployeePageRequestDto dto) {
		Pageable pageable = dto.getPageable();
		
		if(dto.getStatus() != null && (dto.getStatus()==1 || dto.getStatus()==2)) {
			return employeeRepositoryPagination.findByStatus(dto.getStatus(),pageable);
		}
		if(dto.getStatus() != null && dto.getStatus() == 3) {
			return employeeRepositoryPagination.findByStatus(3, pageable);
		}
		if(dto.getStatus() != null && dto.getStatus() > 3) {
			return Page.empty(pageable);
		}
		return employeeRepositoryPagination.findByStatusNot(3,pageable);
	}

	
	@Override
	public String updateEmployeeStatus(long id, int status) {
		Optional<Employee> empOptional = employeeRepositoryPagination.findById(id);
		if(empOptional.isEmpty()) {
			return ApiStatus.EMPLOYEE_NOT_FOUND.getMessage();
		}
		Employee employee = empOptional.get();
		employee.setStatus(status);
		employeeRepositoryPagination.save(employee);
		ApiStatus statusMessage;
		if(status==1) {
			return ApiStatus.EMPLOYEE_ACTIVATED.getMessage();
		} else if(status==2) {
			return ApiStatus.EMPLOYEE_INACTIVATED.getMessage();
		} else if(status==3) {
			return ApiStatus.EMPLOYEE_DELETED.getMessage();
		}else {
			return ApiStatus.INVALID_STATUS.getMessage();
		}
	}
	
	
	@Override
	public String updateOrAddEmployee(EmployeePageRequestDto dto) {
	    if (dto.getId() == 0) {   
	     
	        Employee newEmployee = new Employee();
	        newEmployee.setName(dto.getSearchKeyword());
	        newEmployee.setDepartment(dto.getDepartment());
	        newEmployee.setSalary(dto.getSalary());
	        newEmployee.setStatus(1);
	        newEmployee.setEmailId(dto.getEmailId());
	        newEmployee.setPassword(passwordEncoder.encode(dto.getPassword()));
	        employeeRepositoryPagination.save(newEmployee);
	        return ApiStatus.EMPLOYEE_ADDED_SUCCESSFULLY.getMessage(); 
	    }
	    Optional<Employee> existingEmployee = employeeRepositoryPagination.findById(dto.getId());
	    if (existingEmployee.isPresent()) {
	        Employee updatedEmployee = existingEmployee.get();
	        updatedEmployee.setName(dto.getSearchKeyword());
	        updatedEmployee.setDepartment(dto.getDepartment());
	        updatedEmployee.setSalary(dto.getSalary());
	        if(dto.getStatus() != null) {
	        	updatedEmployee.setStatus(dto.getStatus());	
	        }
	        
	        employeeRepositoryPagination.save(updatedEmployee);
	        return ApiStatus.EMPLOYEE_UPDATED_SUCCESSFULLY.getMessage(); 
	    } else {
	        return ApiStatus.EMPLOYEE_DOES_NOT_EXIST.getMessage(); 
	    }
	}

	@Override
	public boolean existsById(Long id) {
		return employeeRepositoryPagination.existsById(id);
	}
}
