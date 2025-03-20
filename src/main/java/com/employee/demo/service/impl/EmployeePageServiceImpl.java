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
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.response.ResponseEmployee;
import com.employee.demo.service.EmployeePageService;

@Service
public class EmployeePageServiceImpl implements EmployeePageService {

    @Autowired
    private EmployeePageRepository employeePageRepository;

	@Override
	public EmployeePageResponse getEmployeesWithPaginationAndSorting(EmployeePageRequest request) {

	    Sort.Direction direction =request.getSortDirection().equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
	    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(direction,request.getSortBy() ));

	    String Keyword = (request.getKeyword() == null || request.getKeyword().trim().isEmpty()) ? "" : request.getKeyword();
	   

	    Page<Employee> employeePage = employeePageRepository.searchEmployees(request.getKeyword(),  pageable);

	    List<ResponseEmployee> responseEmployees = employeePage.getContent().stream()
	            .map(emp -> new ResponseEmployee(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary(),emp.getStatus()))
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

	
	
	
	@Override
	public EmployeePageResponse getEmployeesByStatus(int pageNo, int pageSize, int status) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
	    Page<Employee> employeesPage;

	    if (status == 1 || status == 2 || status == 3) {
	        employeesPage = employeePageRepository.findByStatus(status, pageable);
	    } else if (status == 0) {
	        employeesPage = employeePageRepository.findByStatusNot(3, pageable);
	    } else {
	    	 return new EmployeePageResponse();
	    }

	 
	    List<ResponseEmployee> responseEmployees = employeesPage.getContent()
	        .stream()
	        .map(emp -> new ResponseEmployee(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary(),emp.getStatus()))
	        .collect(Collectors.toList());
	    
	   

	    return new EmployeePageResponse(
	        responseEmployees,
	        employeesPage.getPageable(),
	        employeesPage.getTotalElements(),
	        employeesPage.isLast(),
	        employeesPage.getTotalPages(),
	        employeesPage.getSize(),
	        employeesPage.getNumber(),
	        employeesPage.getSort(),
	        employeesPage.isFirst(),
	        employeesPage.getNumberOfElements(),
	        employeesPage.isEmpty()
	    );

	}
}

