package com.employee.demo.service;

import java.util.Map;

import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;

public interface EmployeePageService {
	EmployeePageResponse getEmployeesWithPaginationAndSorting( EmployeePageRequest request);
	EmployeePageResponse getEmployeesByStatus(int pageNo, int pageSize, int status);

}
