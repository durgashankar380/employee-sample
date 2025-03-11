package com.employee.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.employee.demo.model.Employee;

import dto.EmployeePageRequestDto;

public interface EmployeeServicePagination {

	Page<Employee> searchEmployees(EmployeePageRequestDto dto);
	Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto);
	Page<Employee> getAllEmployeeUsingPaginationList(EmployeePageRequestDto dto);
	Page<Employee> getEmployeeByStatus(EmployeePageRequestDto dto);
	String updateEmployeeStatus(long id, int status);
	String updateOrInsertEmployee(Employee employee);
	boolean existsById(Long id);
}
