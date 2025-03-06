package com.employee.demo.controller;


import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.service.EmployeeServicePagination;
import dto.EmployeePageRequestDto;

@RestController
@RequestMapping("/employeeP")
public class EmployeeControllerPagination {

	private EmployeeServicePagination employeeServicePagination; 
	
	public EmployeeControllerPagination(EmployeeServicePagination employeeServicePagination) {
		this.employeeServicePagination=employeeServicePagination;
	}
	
	/**
	 * Fetch all employees using pagination
	 * @param dto EmployeePageRequestDto containing pagination details such as page number, page size, and sorting.
	 * @return get all employees by pagination
	 */
	
	@PostMapping
	public Page<Employee> getAllEmployeeUsingPagination(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPagination(dto);
	}
	
	
	/**
	 * Fetch all Employees apply sorting and then pagination
	 * @param dto EmployeePageRequestDto containing pagination details such as page number, page size, sort column, sort direction
	 * @return fetch all data and then apply pagination
	 */
	
	@PostMapping("/list")
	public Page<Employee> getAllEmployeeUsingPaginationList(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPaginationList(dto);
	}
	
	/**
	 * Search employee with pagination, and multiple searches
	 * @param dto EmployeePageRequestDto containing pagination details and search filters.
	 * @return search result with pagination
	 */
	
	@PostMapping("/search")
	public Page<Employee> searchEmployees(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.searchEmployees(dto);
		}
	}
