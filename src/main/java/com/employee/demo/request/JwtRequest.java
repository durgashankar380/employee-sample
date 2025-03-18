package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequest {

	   private String email;
	    private String password;
}
