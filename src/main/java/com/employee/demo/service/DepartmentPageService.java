package com.employee.demo.service;

import com.employee.demo.request.EmployeePageRequest;
import org.springframework.http.ResponseEntity;

public interface DepartmentPageService {
    ResponseEntity<?> getPage(EmployeePageRequest request);

    ResponseEntity<?> manageStatus(Long id, Integer status);
}
