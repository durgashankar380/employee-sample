package com.employee.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequestPage;

public interface EmployeePaginationService {

	List<Employee> getAllEmployee();

	List<Employee> findEmployeeWithSorting(String field);
	
	Page<Employee> findEmployeeWithPagination(EmployeeRequestPage request);

	Page<Employee> findEmployeeWithPaginationAndSorting(EmployeeRequestPage request);

	

}
