package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentPageRequest {
	private int page = 0;         
    private int size = 10;        
    private String sortBy = "id";
    private String sortDirection = "asc";
    private String keyword;      
    private int status=0;  
}
