package com.employee.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.service.EmployeePaginationService;

@RestController
@RequestMapping("/employee-page")
public class EmployeePaginationController {

	@Autowired
	private EmployeePaginationService service;

	@GetMapping("/employee-sort-by/{field}")
	public EmployeePageResponse<List<Employee>> getEmployeesWithSort(@PathVariable String field) {
		List<Employee> allEmployees = service.findEmployeeWithSorting(field);
		return new EmployeePageResponse<>(allEmployees.size(),
				allEmployees.isEmpty() ? "no record found !!" : "data found succesfully.", allEmployees);
	}

	@GetMapping("/pagination")
	public EmployeePageResponse<Page<Employee>> getEmployeesWithPagination(@RequestBody EmployeePageRequest request) {
		Page<Employee> employeePage = service.findEmployeeWithPagination(request);
		return new EmployeePageResponse<>(employeePage.getContent().size(),
				employeePage.getContent().isEmpty() ? "no record found !!" : "data found succesfully.", employeePage);
	}

	@GetMapping("/pagination-sort")
	public EmployeePageResponse<Page<Employee>> getEmployeesWithPaginationAndSorting(
			@RequestBody EmployeePageRequest request) {
		Page<Employee> employeePage = service.findEmployeeWithPaginationAndSorting(request);
		return new EmployeePageResponse<>(employeePage.getContent().size(),
				employeePage.getContent().isEmpty() ? "no record found !!" : "data found succesfully.", employeePage);
	}

	@GetMapping("/search-by")
	public EmployeePageResponse<Page<Employee>> getEmployeesWithSearch(@RequestBody EmployeePageRequest request) {
		Page<Employee> employees = service.searchEmployees(request);
		return new EmployeePageResponse<>(employees.getContent().size(),
				employees.getContent().isEmpty() ? "no record found !!" : "data found succesfully.", employees);
	}

	@PostMapping("/status")
	public EmployeePageResponse<Page<Employee>> getEmployees(@RequestBody EmployeePageRequest request) {
		Page<Employee> employeePage = service.getEmployees(request);
		return new EmployeePageResponse<>(employeePage.getContent().size(),
				employeePage.getContent().isEmpty() ? "no record found !!" : "data found succesfully", employeePage);
	}

	@GetMapping("/manage-status")
	public ResponseEntity<String> updateStatus(@RequestParam long id, @RequestParam int status) {
		String message = service.updateStatus(id, status);
		return new ResponseEntity<>(message, HttpStatus.OK); 
	}
	
	@PostMapping("/add-or-update")
	public ResponseEntity<String> addOrUpdateWithId( @RequestBody EmployeeRequest request) {
		String message = service.addOrUpdateWithId(request);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
