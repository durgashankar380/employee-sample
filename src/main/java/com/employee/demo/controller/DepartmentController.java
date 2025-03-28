package com.employee.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.DepartmentRequestDto;
import com.employee.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	 private final DepartmentService departmentService;

	    public DepartmentController(DepartmentService departmentService) {
	        this.departmentService = departmentService;
	    }

	    @PostMapping("/add-update")
	    public ResponseEntity<String> addOrUpdateDepartment(@RequestBody DepartmentRequestDto departmentRequest) {
	        return departmentService.addOrUpdateDepartment(departmentRequest);
	    }
	}

