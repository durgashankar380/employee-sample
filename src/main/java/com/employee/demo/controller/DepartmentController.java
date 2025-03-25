package com.employee.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentRequestDto;
import com.employee.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartmentWithEmployees(@RequestBody DepartmentRequestDto departmentRequestDto) {
        Department savedDepartment = departmentService.saveDepartmentWithEmployees(departmentRequestDto);
        return ResponseEntity.ok(savedDepartment);
    }
}
