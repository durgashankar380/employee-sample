package com.employee.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.request.EmployeeStatusRequest;
import com.employee.demo.response.EmployeeStatusResponse;
import com.employee.demo.service.EmployeeStatusService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeStatusController {

	 @Autowired
	    private EmployeeStatusService employeeStatusService;

	    @GetMapping("/manage-status")
	    public EmployeeStatusResponse manageEmployeeStatus(
	            @RequestParam Long id, 
	            @RequestParam int status) {
	        
	        EmployeeStatusRequest request = new EmployeeStatusRequest(id, status);
	        return employeeStatusService.manageEmployeeStatus(id,status);
	    }
	    

	    @PostMapping("/add-update")
	    public EmployeeStatusResponse addOrUpdateEmployeeStatus(@RequestBody EmployeeStatusRequest request) {
	        return employeeStatusService.addOrUpdateEmployeeStatus(request);
	    }
}
