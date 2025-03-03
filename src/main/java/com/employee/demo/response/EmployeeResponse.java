package com.employee.demo.response;

import com.employee.demo.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

	private Long id;
	private String name;
	private String department;
	private double salary;

	public EmployeeResponse(Employee employee) {
		this.id=employee.getId();
		this.name=employee.getName();
		this.department=employee.getDepartment();
		this.salary=employee.getSalary();
		
	}
}
