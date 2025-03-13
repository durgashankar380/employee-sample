package com.employee.demo.service;

import java.util.Map;

import com.employee.demo.response.EmployeePageResponse;

public interface EmployeePageService {
	EmployeePageResponse getEmployeesWithPaginationAndSorting(int page, int size, String sortBy, String sortDirection, String keyword,int status) ;

	//Map<String, Object> getEmployeesByStatus(int pageNo, int pageSize, int status);
	 EmployeePageResponse getEmployeesByStatus(int pageNo, int pageSize, int status);
}
