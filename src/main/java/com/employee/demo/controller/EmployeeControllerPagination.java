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
import com.employee.demo.service.EmployeeServicePagination;
import dto.EmployeePageRequestDto;


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
	
	@PostMapping
	public Page<Employee> getAllEmployeeUsingPagination(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPagination(dto);
	}
	
	/**
	 * Fetch all Employees apply sorting and then pagination
	 * @param dto EmployeePageRequestDto containing pagination details such as page number, page size, sort column, sort direction.
	 * @return fetch all data and then apply pagination
	 */
	
	@GetMapping("/list")
	public Page<Employee> getAllEmployeesUsingPaginationList(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPaginationList(dto);
	}
	
	/**
	 * Search employee with pagination, and multiple searches
	 * @param dto EmployeePageRequestDto containing pagination details and search filters.
	 * @return search result with pagination
	 */
	
	@GetMapping("/search")
	public Page<Employee> searchEmployees(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.searchEmployees(dto);
		}
	
	@GetMapping("/status-pagination")
	public Page<Employee> getEmployeeByStatus(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getEmployeeByStatus(dto);
		}
	
	@GetMapping("/manage-status")
	public String manageStatusOfEmployee(@RequestParam long id, @RequestParam int status) {
		return employeeServicePagination.updateEmployeeStatus(id,status);
		}
	
	@GetMapping("/update-insert-employee")
	public ResponseEntity<String> updateOrInsertEmployee(@RequestBody Employee employee) {
		String msg = employeeServicePagination.updateOrInsertEmployee(employee);
		return new ResponseEntity<>(msg,HttpStatus.OK);
		}
	}
