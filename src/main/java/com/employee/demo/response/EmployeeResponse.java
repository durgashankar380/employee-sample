package com.employee.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeResponse {

	private long employeeId;
	private String name;
	private String department;
	private double Salary;

}
