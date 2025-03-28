package com.employee.demo.response;

import java.util.List;
import java.util.stream.Collectors;

import com.employee.demo.model.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
		private long departmentId;
		private String departmentName;
		private String location;
		private String description;
		private Integer status;
		private List<ResponseEmployee> employees;
	 
		// Constructor
		public DepartmentResponse(Department department) {
		        this.departmentId = department.getId();
		        this.departmentName = department.getName();
		        this.location = department.getLocation();
		        this.description = department.getDescription();
		        this.status = department.getStatus();
		        this.employees = department.getEmployee().stream()
		                .map(ResponseEmployee::new) // Convert Employee -> EmployeeResponse
		                .collect(Collectors.toList());
		
		}
	 
	}

