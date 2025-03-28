package com.employee.demo.response;

import com.employee.demo.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployee {
		 private long id;
		    private String name;
		    private String department;
		    private double salary;
		    private int status;
		    private String emailId;
	 
		    // Constructor
		    public ResponseEmployee(Employee employee) {
		        this.id = employee.getId();
		        this.name = employee.getName();
		        this.department = employee.getDepartment();
		        this.salary = employee.getSalary();
		        this.status = employee.getStatus();
		        this.emailId = employee.getEmailId();
		    }
	 
	}
