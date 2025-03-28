package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestedDepartmentEmployee {

	private long id;
	private String name;
	private double salary;
	private int status;
	private String emailId;
	private String password;

}
