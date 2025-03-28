package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmployee {
	private Long id;
    private String name;
    private double salary;
    private int status;
    private String emailId;
    private String password;
}
