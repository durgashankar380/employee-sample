package com.employee.demo.service;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentRequestDto;

public interface DepartmentService {
	
	Department saveDepartmentWithEmployees(DepartmentRequestDto departmentRequestDto);



}
