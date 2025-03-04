package com.employee.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepositoryPagination;
import com.employee.demo.response.EmployeeResponse;
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
	 * @author ritik
	 * @param dto EmployeePageRequestDto containing pagination details such as page number, page size, and sorting.
	 * @return get all employes by pagination
	 */
	
	@PostMapping
	public Page<Employee> getAllEmployeeUsingPagination(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPagination(dto);
	}
	
	
	/**
	 * @author ritik
	 * @param dto EmployeePageRequestDto containing pagination details such as page number, page size, sort column, sort direction
	 * @return fetch all data and then apply pagination
	 */
	
	@PostMapping("/list")
	public Page<Employee> getAllEmployeeUsingPaginationList(@RequestBody EmployeePageRequestDto dto) {
		return employeeServicePagination.getAllEmployeeUsingPaginationList(dto);
		
	}
}
