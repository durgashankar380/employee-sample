package com.employee.demo.controller;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.service.EmployeeServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagination")
public class EmployeeControllerPagination {

    @Autowired
    private EmployeeServicePage servicePage;


    @PostMapping("/getAllPage")
    public Page<Employee> getAllEmployeePage(@RequestBody EmployeePageRequest request) {
        return servicePage.getAllEmployeePage(request);
    }

    @GetMapping("/getPageByNameSalaryDepartment")
    public EmployeePageResponse findByNameAndSalaryAndDepartment(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) double salary,
            @RequestParam(required = false) String department,
            EmployeePageRequest request) {
        return servicePage.findByNameAndSalaryAndDepartment(name, salary, department, request);
    }


    @PutMapping("manageStatus")
    public ResponseEntity<?> manageStatus(@RequestParam int status, @RequestParam long employeeId) {
        return servicePage.updateStatus(status, employeeId);
    }

    @PostMapping("searchByStatus")
    public ResponseEntity<?> searchByStatus(@RequestBody EmployeePageRequest request) {
        return servicePage.searchByStatus(request);
    }

}

