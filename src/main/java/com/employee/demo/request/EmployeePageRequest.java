package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePageRequest {
	private int pageNumber;
	private int pageSize;
	private String sortBy;
	private String keyword;
	private Double salary;
	private int status;

}
