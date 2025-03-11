package com.employee.demo.service;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EmployeeServicePage {

    Page<Employee> getAllEmployeePage(EmployeePageRequest request);

    EmployeePageResponse findByNameAndSalaryAndDepartment(String name, double salary, String department, EmployeePageRequest request);

    ResponseEntity<?> updateStatus(int status, long employeeId);

    ResponseEntity<?> searchByStatus(int status, EmployeePageRequest request);
}


//irctc partioning and shardining