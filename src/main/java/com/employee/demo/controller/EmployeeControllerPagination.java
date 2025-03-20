package com.employee.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequestDto;
import com.employee.demo.service.EmployeeServicePagination;


@RestController
@RequestMapping("/employee-pagination")
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
	
	@PostMapping("/pagination")
	public Page<Employee> getAllEmployeeUsingPagination(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPagination(dto);
	}
	
	/**
	 * Fetch all Employees apply sorting and then pagination
	 * @param dto EmployeePageRequestDto containing pagination details such as page number, page size, sort column, sort direction.
	 * @return fetch all data and then apply pagination
	 */
	
	@PostMapping("/pagination-sort")
	public Page<Employee> getAllEmployeesUsingPaginationSort(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPaginationSort(dto);
	}
	
	/**
	 * Search employee with paginatio(n, and multiple searches
	 * @param dto EmployeePageRequestDto containing pagination details and search filters.
	 * @return search result with pagination
	 */
	
	@PostMapping("/search-by")
	public Page<Employee> searchEmployees(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.searchEmployees(dto);
		}
	
	@PostMapping("/pagination-status")
	public Page<Employee> getEmployeeByStatus(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getEmployeeByStatus(dto);
		}
	
	@GetMapping("/manage-status")
	public String manageStatusOfEmployee(@RequestParam long id, @RequestParam int status) {
		return employeeServicePagination.updateEmployeeStatus(id,status);
		}
	
	@PostMapping("/add-update")
	public ResponseEntity<String> updateOrAddEmployee(@RequestBody EmployeePageRequestDto dto) {
		String message = employeeServicePagination.updateOrAddEmployee(dto);
		return new ResponseEntity<>(message,HttpStatus.OK);
		}
	}
