package com.employee.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.response.DepartmentResponse;

public interface DepartmentServices {

	String addOrUpdateDepartment(DepartmentRequest departmentRequest);

	List<DepartmentResponse> getAllDepartment();

	Page<DepartmentResponse> getAllByPagination(DepartmentPageRequest request);

	String deleteDepartment(Integer departmentId);

}
