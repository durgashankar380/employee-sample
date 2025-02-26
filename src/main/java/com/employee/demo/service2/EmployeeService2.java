package com.employee.demo.service2;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeService2 {
    List<Employee> getTopThree();

    List<EmployeeResponse> getAll();

    List<EmployeeResponse> getSecondHighestSalary();

    String getDepartmentWithHighestSalary();

    List<EmployeeResponse> getEmpEarnMoreThanAvgSalary();

    Character getMostCommonFirstLetter();

    List<EmployeeResponse> addEmployeeList(List<EmployeeRequest> requestList);

    Map<String, Long> countPerDepartment();

    List<String> getEmployeesList();

    List<EmployeeResponse> getSortedSalaryDesc();

    Set<String> getUniqueEmployeeDepartments();

    Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

    Map<String, Double> getTotalSalaryPerDepartment();

    Map<Long, EmployeeResponse> getEmployeesByIdMap();

    EmployeeResponse addEmployee(EmployeeRequest employeeRequest);
}
