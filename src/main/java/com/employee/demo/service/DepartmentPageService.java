package com.employee.demo.service;

import com.employee.demo.request.EmployeePageRequest;
import org.springframework.http.ResponseEntity;


public interface DepartmentPageService {

    public ResponseEntity<?> getPage(EmployeePageRequest request);
    public ResponseEntity<?> manageStatus(Long id, Integer status);
}
