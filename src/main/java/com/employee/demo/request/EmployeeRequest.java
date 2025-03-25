package com.employee.demo.request;

import com.employee.demo.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private Long id;
	private String name;
	private Double salary;
	private String email;
	private String password;
    private DepartmentRequest department;
}
