package com.employee.demo.service;


import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

import java.util.*;

public interface EmployeeService2 {
    void addEmployee(EmployeeRequest employeeRequest);

    List<Employee> getAllEmployee();

    List<String> getAllEmployeeName();

    List<Employee> getEmployeesSortedBySalary();

    Set<String> getUniqueEmployeeDepartments();

    List<Employee> queueOfEmployee();

    List<Employee> stackOfEmployee();

    List<Employee> getTopThree();

    List<Object> getTotalSalaryByDepartment();

    String findDepartmentWithHighestTotalSalary();

    List<Employee> findEmployeesAboveDepartmentAverageSalary();

    List<Object[]> findMostCommonFirstLetter();

    Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

    List<Employee> findEmployeesWithSecondHighestSalary();

    Map<Long, EmployeeResponse> getEmployeeById(Long id);

    Map<String, Long> getcountperDepartment();

    Employee updateEmpById(long id, Employee employeeEntity);


}


