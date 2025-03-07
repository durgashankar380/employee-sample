package com.employee.demo.controller;

import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.service.impl.EmployeeServicePageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EmployeePage")
public class EmployeeControllerPage {
    @Autowired
    private EmployeeServicePageImpl servicePage;

    @GetMapping("/getPage")
    public EmployeePageResponse getPage(@RequestBody EmployeePageRequest request){
        return servicePage.getEmployeePage(request);
    }

}
