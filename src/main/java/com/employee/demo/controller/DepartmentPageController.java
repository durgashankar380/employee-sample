package com.employee.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.service.DepartmentPageService;

@RestController
@RequestMapping("/departments")
public class DepartmentPageController {
	@Autowired
    private DepartmentPageService departmentPageService;

    @PostMapping("/search")
    public ResponseEntity<Page<Department>> searchDepartments(@RequestBody DepartmentPageRequest request) {
        Page<Department> result = departmentPageService.searchDepartments(request);
        return ResponseEntity.ok(result);
    }
}
