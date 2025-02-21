package com.employee.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEmployee {

	private Long id;
	private String name;
	private String department;
	private double salary;
}
