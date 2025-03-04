package com.employee.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Employee;

import dto.EmployeePageRequestDto;

public interface EmployeeServicePagination {
	Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto);
	Page<Employee> getAllEmployeeUsingPaginationList(EmployeePageRequestDto dto);
}
