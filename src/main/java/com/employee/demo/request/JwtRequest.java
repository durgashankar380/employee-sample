package com.employee.demo.request;

import lombok.Data;

@Data

public class JwtRequest {
	private String emailId;
	private String password;
}
