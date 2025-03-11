package com.employee.demo.service;

import com.employee.demo.request.EmployeeStatusRequest;
import com.employee.demo.response.EmployeeStatusResponse;

public interface EmployeeStatusService {
	// Page<Employee> findByStatusIn(List<Integer> statuses, Pageable pageable);

	 EmployeeStatusResponse manageEmployeeStatus(EmployeeStatusRequest request) ;
	 
	 EmployeeStatusResponse addOrUpdateEmployeeStatus(EmployeeStatusRequest request);
}
