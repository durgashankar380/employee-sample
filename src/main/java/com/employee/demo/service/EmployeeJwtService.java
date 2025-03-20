package com.employee.demo.service;

import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.ForgetPasswordRequest;
import com.employee.demo.request.ResetPasswordRequest;

public interface EmployeeJwtService {

	String register(EmployeeRequest request);
	
	String forgetPassword(ForgetPasswordRequest request);
	
	String resetPassword(ResetPasswordRequest request);

}
