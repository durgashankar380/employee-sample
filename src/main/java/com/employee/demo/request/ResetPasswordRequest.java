package com.employee.demo.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	private String emailId;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
}
