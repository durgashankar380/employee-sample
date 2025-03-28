package com.employee.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentPageRequest {
	private int pageNo;
	private int pageSize;
	private String keyword;
	private String sortBy;
	private String sortDir;
	private int status;
}
