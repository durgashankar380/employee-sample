package com.employee.demo.service;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.request.DepartmentRequestWithList;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DepartmentService {
    List<Employee> getEmployeesByDepartment(long id);

    ResponseEntity<?> createEmployee(DepartmentRequestWithList request);

    Map<String, Double> getTotalSalaryByDepartment();

    Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

    Set<String> getUniqueEmployeeDepartments();

    ResponseEntity<List<EmployeeResponse>>getAllEmpByDepartment(String name);

    void deleteDepartment(Long id);

    List<DepartmentResponse> getAllDepartments();

    // ResponseEntity<?> getPage(EmployeePageRequest request);

  //  ResponseEntity<?> manageStatus(Long id, Integer status);
}
