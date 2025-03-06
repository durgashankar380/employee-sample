package com.employee.demo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepositoryPagination;
import com.employee.demo.service.EmployeeServicePagination;
import dto.EmployeePageRequestDto;

@Service
public class EmployeeServiceImplPagination implements EmployeeServicePagination {

		private final EmployeeRepositoryPagination employeeRepositoryPagination;
	
	public EmployeeServiceImplPagination(EmployeeRepositoryPagination employeeRepositoryPagination) {
		this.employeeRepositoryPagination = employeeRepositoryPagination;
	}

	// retrieve all employees using pagination
	
	@Override
	public Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto) {
		Pageable pageable = dto.getPageable();
		return employeeRepositoryPagination.findAll(pageable);
	}

	// SEARCH EMPLOYEE USING ID , NAME , SALARY , DEPARTMENT.
	
	@Override
	public Page<Employee> searchEmployees(EmployeePageRequestDto dto) {
		Pageable pageable = dto.getPageable();
		return employeeRepositoryPagination.searchEmployees(
				dto.getSearchKeyword(),
				dto.getId(),
				dto.getSalary(),
				dto.getDepartment(),
				pageable
				);
	}	

	// retrieves list of employees using pagination and sorting
	
	@Override
	public Page<Employee> getAllEmployeeUsingPaginationList(EmployeePageRequestDto dto) {
		return employeeRepositoryPagination.findAll(dto.getPageable());
	}

	

}
