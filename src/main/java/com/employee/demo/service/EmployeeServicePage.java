package com.employee.demo.service;

import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.request.EmployeePageRequest;
import org.springframework.http.ResponseEntity;

public interface EmployeeServicePage {
    EmployeePageResponse getEmployeePage(EmployeePageRequest request);

    ResponseEntity<?> updateStatus(Long id, int status);

    EmployeePageResponse getPageByNameDepartmentSalary(String name, String department, Double salary, EmployeePageRequest request);

    EmployeePageResponse getEmployeeAllPage(EmployeePageRequest request);

    ResponseEntity<?> searchByStatus(EmployeePageRequest request);
}
