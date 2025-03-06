package com.employee.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequest;

public interface EmployeePaginationService {

	List<Employee> getAllEmployee();

	List<Employee> findEmployeeWithSorting(String field);
	
	Page<Employee> findEmployeeWithPagination(EmployeePageRequest request);

	Page<Employee> findEmployeeWithPaginationAndSorting(EmployeePageRequest request);

	Page<Employee> searchEmployees(EmployeePageRequest request);

}
