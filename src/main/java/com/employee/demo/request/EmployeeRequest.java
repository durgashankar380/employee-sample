package com.employee.demo.request;

import lombok.Data;

@Data
public class EmployeeRequest {

	private long employeeId;
	private String name;
	private String department;
	private double salary;
}
