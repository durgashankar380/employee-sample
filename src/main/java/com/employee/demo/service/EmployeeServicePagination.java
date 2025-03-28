package com.employee.demo.service;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequestDto;

public interface EmployeeServicePagination {

	Page<Employee> searchEmployees(EmployeePageRequestDto dto);
	Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto);
	Page<Employee> getAllEmployeeUsingPaginationSort(EmployeePageRequestDto dto);
	Page<Employee> getEmployeeByStatus(EmployeePageRequestDto dto);
	String updateEmployeeStatus(long id, int status);
	boolean existsById(Long id);
	String updateOrAddEmployee(EmployeePageRequestDto dto);
}
