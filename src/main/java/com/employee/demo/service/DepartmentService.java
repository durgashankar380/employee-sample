package com.employee.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.response.DepartmentResponse;


public interface DepartmentService {

	String addOrUpdateDepartment(DepartmentRequest departmentRequest);
	List<DepartmentResponse> getAllDepartments();
	Department getDepartmentByName(String name);
	Department getDepartmentById(Long id);
	String updateDepartment(Long id, Department department);
	String deleteDepartment(Long id);
	
	Page<DepartmentResponse> getAllByPagination(DepartmentPageRequest request);
	}
