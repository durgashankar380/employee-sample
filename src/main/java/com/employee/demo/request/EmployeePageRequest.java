package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePageRequest {

	    private int page;
	    private int size;
	    private String sortBy;
	    private String sortDirection;
	    private String keyword;
	    private String Dkeyword;
}
