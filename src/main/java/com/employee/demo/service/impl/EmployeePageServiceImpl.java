package com.employee.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeePageRepository;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.response.ResponseEmployee;
import com.employee.demo.service.EmployeePageService;

@Service
public class EmployeePageServiceImpl implements EmployeePageService{
	@Autowired
	private EmployeePageRepository employeePageRepository;
	
	@Override
	public EmployeePageResponse getEmployeesWithPaginationAndSorting(int page, int size, String sortBy, String sortDirection, String keyword) {

	    Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
	    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

	    keyword = (keyword == null || keyword.trim().isEmpty()) ? "" : keyword;
	   

	    Page<Employee> employeePage = employeePageRepository.searchEmployees(keyword,  pageable);

	    List<ResponseEmployee> responseEmployees = employeePage.getContent().stream()
	            .map(emp -> new ResponseEmployee(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()))
	            .collect(Collectors.toList());

	    return new EmployeePageResponse(
	            responseEmployees,
	            employeePage.getPageable(),
	            employeePage.getTotalElements(),
	            employeePage.isLast(),
	            employeePage.getTotalPages(),
	            employeePage.getSize(),
	            employeePage.getNumber(),
	            employeePage.getSort(),
	            employeePage.isFirst(),
	            employeePage.getNumberOfElements(),
	            employeePage.isEmpty()
	    );
	}


}
