package com.employee.demo.controller;

import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.impl.EmployeeServicePageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/EmployeePage")
public class EmployeeControllerPage {
    @Autowired
    private EmployeeServicePageImpl servicePage;

    @GetMapping("/getPage")
    public EmployeePageResponse getPage(@RequestBody EmployeePageRequest request){
        return servicePage.getEmployeePage(request);
    }
    @GetMapping("/getAllPage")
    public EmployeePageResponse getAllPage(@RequestBody EmployeePageRequest request){
        return servicePage.getEmployeeAllPage(request);
    }

    @GetMapping("/getPageByNameDepartmentSalary")
    public EmployeePageResponse getPageByNameDepartmentSalary(@RequestParam String name,@RequestParam String department , @RequestParam Double salary,@RequestBody EmployeePageRequest request){
        return servicePage.getPageByNameDepartmentSalary(name ,department,salary,request);
    }


    @PostMapping("/manageStatus")
    public ResponseEntity<?> manageStatus(@RequestParam Long id, @RequestParam int status){
        return servicePage.updateStatus(id,status);
    }

    @GetMapping("/searchByStatus")
    public ResponseEntity<?> searchByStatus(@RequestBody EmployeePageRequest request){
        return servicePage.searchByStatus(request);
    }

}
