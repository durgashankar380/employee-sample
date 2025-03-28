package com.employee.demo.service;

import com.employee.demo.model.Employee;
import com.employee.demo.request.ForgetPasswordRequest;
import com.employee.demo.request.ResetPasswordRequest;

public interface EmployeeJwtService {

	String register(Employee employee);
	
	String forgetPassword(ForgetPasswordRequest request);
	
	String resetPassword(ResetPasswordRequest request);

}
