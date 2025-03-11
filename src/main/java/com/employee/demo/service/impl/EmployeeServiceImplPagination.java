package com.employee.demo.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepositoryPagination;
import com.employee.demo.service.EmployeeServicePagination;
import dto.EmployeePageRequestDto;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImplPagination implements EmployeeServicePagination {

		private final EmployeeRepositoryPagination employeeRepositoryPagination;
	
	public EmployeeServiceImplPagination(EmployeeRepositoryPagination employeeRepositoryPagination) {
		this.employeeRepositoryPagination = employeeRepositoryPagination;
	}

	// retrieve all employees using pagination
	
	
	@Override
	public Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto) {
		return employeeRepositoryPagination.findAll(dto.getPageable());
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

	
	
	@Override
	public Page<Employee> getEmployeeByStatus(EmployeePageRequestDto dto) {
		Pageable pageable = dto.getPageable();
		
		if(dto.getStatus() != null && (dto.getStatus()==1 || dto.getStatus()==2)) {
			return employeeRepositoryPagination.findByStatus(dto.getStatus(),pageable);
		}
		if(dto.getStatus() != null && dto.getStatus() == 3) {
			return employeeRepositoryPagination.findByStatus(3, pageable);
		}
		if(dto.getStatus() != null && dto.getStatus() > 3) {
			return Page.empty(pageable);
		}
		return employeeRepositoryPagination.findByStatusNot(3,pageable);
	}

	
	@Override
	public String updateEmployeeStatus(long id, int status) {
		Optional<Employee> empOptional = employeeRepositoryPagination.findById(id);
		if(empOptional.isEmpty()) {
			return "Employee with id "+ id + " not found";
		}
		Employee employee = empOptional.get();
		employee.setStatus(status);
		employeeRepositoryPagination.save(employee);
		String statusMessage;
		if(status==1) {
			statusMessage = " Activated successfully..";
		} else if(status==2) {
			statusMessage = " Inactivated successfully..";
		} else if(status==3) {
			statusMessage = " deleted successfully..";
		}else {
			return "Invalid Status";
		}
		return "Employee with ID " + id + statusMessage;
	}
	
	
	@Override
	public String updateOrInsertEmployee(Employee employee) {
	    if (employee.getId() == null || employee.getId() == 0) {   
	        employee.setStatus(1);
	        Employee savedEmployee = employeeRepositoryPagination.save(employee);
	        return "Employee with id added Successfully"; 
	    }
	    Optional<Employee> existingEmployee = employeeRepositoryPagination.findById(employee.getId());
	    if (existingEmployee.isPresent()) {
	        Employee updatedEmployee = existingEmployee.get();
	        updatedEmployee.setName(employee.getName());
	        updatedEmployee.setDepartment(employee.getDepartment());
	        updatedEmployee.setSalary(employee.getSalary());
	        return "Employee updated Successfully"; 
	    } else {
	        return "Employee does not Exist with this id"; 
	    }
	}


	@Override
	public boolean existsById(Long id) {
		return employeeRepositoryPagination.existsById(id);
	}
}
