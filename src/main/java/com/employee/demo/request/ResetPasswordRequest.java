package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
	private String emailId;
	private String currentPassword;
	private String newPassword;
	private String confirmNewPassword;

}
