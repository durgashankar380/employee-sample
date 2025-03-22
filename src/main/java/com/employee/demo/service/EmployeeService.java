package com.employee.demo.service;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.JwtForgetPasswordRequest;
import com.employee.demo.request.JwtResetPasswordRequest;
import com.employee.demo.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public interface EmployeeService {


    ResponseEntity<?> addEmployee(EmployeeRequest employeeRequest);

    Map<String, Double> getTotalSalaryByDepartment();

    Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

    Set<String> getUniqueEmployeeDepartments();

    Map<Long, EmployeeResponse> getEmployeeById();

    List<EmployeeResponse> getEmployeesSortedBySalary();

    List<String> getAllEmployeeName();

    Map<String, Long> getcountperDepartment();

    Queue<EmployeeResponse> queueOfEmployee();

    Stack<EmployeeResponse> stackOfEmployee();

    Employee updateEmpById(long id, Employee employeeEntity);

    Employee getEmployeeByName(String name);

    ResponseEntity totalSalaryByDepartment(String dept);

    List<EmployeeResponse> addMultipleEmployees(List<EmployeeRequest> employeeRequests);

    List<EmployeeResponse> thirdhighestPaidEmployee();

    List<EmployeeResponse> getEmployeesWithSecondHighestSalary();

    String getDepartmentWithHighestTotalSalary();

    List<Employee> getAverageSalary();

    Character getMostCommonFirstLetter();

    UserDetails loadUserByUsername(String username);

    void getlastLogin(String email);

    ResponseEntity<?> forgetPassword(JwtForgetPasswordRequest request);

    ResponseEntity<String> changePassword(JwtResetPasswordRequest request);
}
