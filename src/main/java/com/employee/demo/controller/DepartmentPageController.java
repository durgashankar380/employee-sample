package com.employee.demo.controller;

import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.service.DepartmentPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentPageController {

    @Autowired
    private DepartmentPageService departmentPageServiceService;

    @GetMapping("/getAllPage")
    public ResponseEntity<?> getPage(EmployeePageRequest request) {
        return departmentPageServiceService.getPage(request);

    }

    @PostMapping("/manage-status")
    public ResponseEntity<?> manageStatus(@RequestParam Long id, @RequestParam Integer status) {
        return departmentPageServiceService.manageStatus(id, status);


    }
}
