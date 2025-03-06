package com.employee.demo.service;

import org.springframework.data.domain.Page;
import com.employee.demo.model.Employee;
import dto.EmployeePageRequestDto;
import org.springframework.data.domain.Pageable;

public interface EmployeeServicePagination {

	Page<Employee> searchEmployees(EmployeePageRequestDto dto);
	Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto);
	Page<Employee> getAllEmployeeUsingPaginationList(EmployeePageRequestDto dto);
}
