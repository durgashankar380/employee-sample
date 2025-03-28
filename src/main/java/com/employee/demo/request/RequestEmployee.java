package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEmployee {
	private Long id;
    private String name;
    private Double salary;
    private int status;
    private String email;
    private String password;
	
	
	
	
   
 }

