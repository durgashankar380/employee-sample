package com.employee.demo.service;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.DepartmentRequestWithEmployeeList;
import com.employee.demo.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface DepartmentService {
    List<Department> getAllDepartments();

    Optional<Department> getDepartmentById(Long id);

    Department saveDepartment(DepartmentRequest department);

    void deleteDepartment(Long id);

    Map<String, Double> getTotalSalaryPerDepartment();

    Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

    Set<String> getUniqueEmployeeDepartments();

    Map<String, Long> countPerDepartment();

    String getDepartmentWithHighestSalary();

    ResponseEntity<?> addEmployeeListByDepartment(DepartmentRequestWithEmployeeList requests);
}
