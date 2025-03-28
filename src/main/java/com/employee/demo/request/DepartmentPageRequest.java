package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentPageRequest {
	private int pageNumber;
	private int pageSize;
	private String keyword;
	private String sortBy;
	private String sortDir;
	private Integer status;

}
