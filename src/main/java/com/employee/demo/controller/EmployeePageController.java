package com.employee.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.service.EmployeePageService;

@RestController
@RequestMapping("/pagination")  
public class EmployeePageController {

    @Autowired
    private EmployeePageService employeePageService;
    
    
    @GetMapping("/paginate")
    public EmployeePageResponse getEmployees(@RequestBody EmployeePageRequest employeePageRequest) {
        return employeePageService.getEmployeesWithPaginationAndSorting(
        		employeePageRequest.getPage(),
        		employeePageRequest.getSize(),
        		employeePageRequest.getSortBy(),
        		employeePageRequest.getSortDirection(),
        		employeePageRequest.getKeyword()
        );
   }
    
    
    @GetMapping("/getAll-pagination")
    public ResponseEntity<EmployeePageResponse> getEmployeesByStatus(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam int status) {

        EmployeePageResponse response = employeePageService.getEmployeesByStatus(pageNo, pageSize, status);

        return ResponseEntity.ok(response);
    }
    
}

