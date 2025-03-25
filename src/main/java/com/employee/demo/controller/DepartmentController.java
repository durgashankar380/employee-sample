package com.employee.demo.controller;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentRequestWithEmployeeList;
import com.employee.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    private Long id;

    @GetMapping("/get_departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("get_by_id/{id}")
    public Optional<Department> getDepartmentById(@PathVariable Long id) {
        this.id = id;
        return departmentService.getDepartmentById(id);
    }



    @DeleteMapping("delete/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PostMapping("/add_employeeList")
    public ResponseEntity<?> addEmployeeListByDepartment(@RequestBody DepartmentRequestWithEmployeeList requests){
        return departmentService.addEmployeeListByDepartment(requests);
    }
}
