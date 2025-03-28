package com.employee.demo.request;

import com.employee.demo.model.Department;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeStatusRequest {
	  public EmployeeStatusRequest(Long id2, int status2) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
	    private String name;
	    private double salary;
	    private int status;
	    private Long departmentId;
	   
		
}
