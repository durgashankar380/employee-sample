package com.employee.demo.controller;

import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.service.DepartmentPageService;
import com.employee.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department_page")
public class DepartmentPageController {
    @Autowired
    private DepartmentPageService departmentPageService;

    @PostMapping("/get_page")
    public ResponseEntity<?> getPage(@RequestBody EmployeePageRequest request){
        return departmentPageService.getPage(request);
    }

    @PostMapping("/manage_status")
    public ResponseEntity<?> manageStatus(@RequestParam Long id,@RequestParam Integer status){
        return departmentPageService.manageStatus(id,status);
    }
}
