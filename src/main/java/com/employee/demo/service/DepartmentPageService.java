package com.employee.demo.service;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentPageRequest;

public interface DepartmentPageService {
	
	Page<Department> searchDepartments(DepartmentPageRequest request);
}
