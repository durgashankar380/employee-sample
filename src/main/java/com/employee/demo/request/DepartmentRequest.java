package com.employee.demo.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
	    private long departmentId;
	    private String departmentName;
	    private String location;
	    private String description;
	    private int status;
	    private List<RequestEmployee> employees;

}
