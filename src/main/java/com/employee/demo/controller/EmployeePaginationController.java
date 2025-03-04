package com.employee.demo.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequestPage;
import com.employee.demo.response.PageResponse;
import com.employee.demo.service.EmployeePaginationService;

@RestController
@RequestMapping("/employeeP")
public class EmployeePaginationController {

	@Autowired
	private EmployeePaginationService service;

	/**
	 * @return get List of all Employee.
	 */
	@GetMapping("/employees")
	public PageResponse<List<Employee>> findAllEmployee() {
		List<Employee> allEmployees = service.getAllEmployee();
		return new PageResponse<>(allEmployees.size(), allEmployees);
	}

	/**
	 * @param field
	 * @return get List of all Employee with sorting by any field.
	 */
	@GetMapping("/employee-sort-by/{field}")
	public PageResponse<List<Employee>> getEmployeesWithSort(@PathVariable String field) {
		List<Employee> allEmployees = service.findEmployeeWithSorting(field);
		return new PageResponse<>(allEmployees.size(), allEmployees);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/pagination")
	public PageResponse<Page<Employee>> getEmployeesWithPagination(@RequestBody EmployeeRequestPage request) {
		Page<Employee> employeePage = service.findEmployeeWithPagination(request);
		return new PageResponse<>(employeePage.getSize(), employeePage);
	}

	/**
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param field
	 * @return get employee in pagination with sorting.
	 */
	@GetMapping("/pagination-sort")
	public PageResponse<Page<Employee>> getEmployeesWithPaginationAndSorting(@RequestBody EmployeeRequestPage request) {
		Page<Employee> employeePage = service.findEmployeeWithPaginationAndSorting(request);
		return new PageResponse<>(employeePage.getSize(), employeePage);
	}
	
	
	

}
