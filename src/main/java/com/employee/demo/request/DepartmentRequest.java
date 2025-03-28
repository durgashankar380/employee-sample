package com.employee.demo.request;

import java.util.List;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
	private Long id;
    private String name;
    private String location;
    private String description;
    private int status;
	
	
      
}

