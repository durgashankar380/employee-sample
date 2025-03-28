package com.employee.demo.service;

import org.springframework.http.ResponseEntity;

import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.DepartmentRequestDto;

public interface DepartmentService {
	 ResponseEntity<String> addOrUpdateDepartment(DepartmentRequestDto departmentRequest);

}
