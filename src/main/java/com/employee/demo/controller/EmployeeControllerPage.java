package com.employee.demo.controller;

import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.impl.EmployeeServicePageImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/EmployeePage")

public class EmployeeControllerPage {
    @Autowired
    private EmployeeServicePageImpl servicePage;

    @PostMapping("/getPage")
    public EmployeePageResponse getPage(@RequestBody EmployeePageRequest request){
        return servicePage.getEmployeePage(request);
    }
    @PostMapping("/getAllPage")
    public EmployeePageResponse getAllPage(@RequestBody EmployeePageRequest request){
        return servicePage.getEmployeeAllPage(request);
    }

    @PostMapping("/getPageByNameDepartmentSalary")
    public EmployeePageResponse getPageByNameDepartmentSalary(@RequestParam String name,@RequestParam String department , @RequestParam Double salary,@RequestBody EmployeePageRequest request){
        return servicePage.getPageByNameDepartmentSalary(name ,department,salary,request);
    }


    @PostMapping("/manageStatus")
    public ResponseEntity<?> manageStatus(@RequestParam Long id, @RequestParam int status){
        return servicePage.updateStatus(id,status);
    }

    @PostMapping("/searchByStatus")
    public ResponseEntity<?> searchByStatus(@RequestBody EmployeePageRequest request){
        return servicePage.searchByStatus(request);
    }

}
