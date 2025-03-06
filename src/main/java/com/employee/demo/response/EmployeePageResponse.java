package com.employee.demo.response;

import org.apache.poi.ss.formula.functions.T;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("hiding")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePageResponse<T> {

	int recordCount;
	T response;
}
