package com.employee.demo.service;

import com.employee.demo.response.EmployeePageResponse;

public interface EmployeePageService {
	 EmployeePageResponse getEmployeesWithPaginationAndSorting(int page, int size, String sortBy, String sortDirection,String keyword);

}
