package com.employee.demo.service;

import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;

public interface EmployeePageService {
	
	EmployeePageResponse getEmployeesByStatus(int pageNo, int pageSize, int status);
	
	EmployeePageResponse getEmployeesWithPaginationAndSorting(EmployeePageRequest request);

}
