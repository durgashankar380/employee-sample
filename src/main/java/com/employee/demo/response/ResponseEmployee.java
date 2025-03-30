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
	private Long id;
	private String name;
	private double salary;
	private int status;
	private String department;
	
	  public ResponseEmployee(Employee emp) {
	        this.id = emp.getId();
	        this.name = emp.getName();
	        this.salary = emp.getSalary();
	        this.status = emp.getStatus();
	        this.department = (emp.getDepartment() != null) ? emp.getDepartment().getName() : "No Department";
	    }

	 
	
}
