package com.employee.demo.response;

import com.employee.demo.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEmployee {

	private Long id;
	private String name;
	private String department;
	private double salary;
	
	 public ResponseEmployee(Employee Employee) {
	        this.id = Employee.getId();
	        this.name = Employee.getName();
	        this.department = Employee.getDepartment();
	        this.salary = Employee.getSalary();
	    }
}
