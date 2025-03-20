package com.employee.demo.service;

import com.employee.demo.request.EmployeeStatusRequest;
import com.employee.demo.response.EmployeeStatusResponse;

public interface EmployeeStatusService {

 EmployeeStatusResponse manageEmployeeStatus(Long id,int status) ;
	
	 EmployeeStatusResponse addOrUpdateEmployeeStatus(EmployeeStatusRequest request);
}
