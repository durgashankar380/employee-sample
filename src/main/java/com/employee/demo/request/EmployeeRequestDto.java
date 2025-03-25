package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private String name;
    private double salary;
    private int status;
    private String email;
    private String password;
	public static void setDepartment(DepartmentRequestDto departmentRequestDto) {
		// TODO Auto-generated method stub
		
	}

}

