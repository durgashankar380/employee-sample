package com.employee.demo.request;

import lombok.Data;

@Data
public class ForgetPasswordRequest {
	private String emailId;
	private String password;
	private String confirmPassword;
}
