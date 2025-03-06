package com.employee.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.service.EmployeePageService;

@RestController
@RequestMapping("/pageination")
public class EmployeePageController {
	
	@Autowired
	private EmployeePageService employeePageService;
	
	 @GetMapping("/paginate")
	    public EmployeePageResponse getEmployees(@RequestBody EmployeePageRequest employeePageRequest) {
	        return employeePageService.getEmployeesWithPaginationAndSorting(
	        		employeePageRequest.getPage(),
	        		employeePageRequest.getSize(),
	        		employeePageRequest.getSortBy(),
	        		employeePageRequest.getSortDirection(),
	        		employeePageRequest.getKeyword()
	        );
	    }
}
