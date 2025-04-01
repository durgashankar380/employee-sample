package com.employee.demo.controller;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.request.DepartmentRequestWithList;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/addAndUpdateEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody DepartmentRequestWithList departmentRequestWithList){
        return departmentService.createEmployee(departmentRequestWithList);
    }


    @GetMapping("/getAllDepartments")
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }


    @GetMapping("/getAllEmpByDepartment")
     public ResponseEntity<List<EmployeeResponse>> getAllEmpByDepartment(@RequestParam String name){
        return departmentService.getAllEmpByDepartment(name);
     }

    @GetMapping("/employees/{id}")
    public List<Employee> getEmployeesByDepartment(@PathVariable long id){
        return departmentService.getEmployeesByDepartment(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }


}
