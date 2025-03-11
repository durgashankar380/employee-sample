package com.employee.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.request.EmployeeRequest;

public interface EmployeePaginationService {

	List<Employee> findEmployeeWithSorting(String field);
	
	Page<Employee> findEmployeeWithPagination(EmployeePageRequest request);

	Page<Employee> findEmployeeWithPaginationAndSorting(EmployeePageRequest request);

	Page<Employee> searchEmployees(EmployeePageRequest request);

	Page<Employee> getEmployees(EmployeePageRequest request);

	String updateStatus(long id, int status);
	
	String addOrUpdateWithId(EmployeeRequest request);
	
	

}
