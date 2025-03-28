package com.employee.demo.response;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEmployee {

	public ResponseEmployee(Employee employee) {
		// TODO Auto-generated constructor stub
	}
	public ResponseEmployee(Long id2, String name2, Double salary2, String email, Department department2) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private String name;
	private double salary;
	private int status;
	private String department;
	 
	
}
