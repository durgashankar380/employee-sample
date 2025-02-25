package com.employee.demo.service;

import java.util.*;

import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeService {


	EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

	Map<String, Double> getTotalSalaryPerDepartment();

	Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

	Set<String> getUniqueEmployeeDepartments();

	Map<Long, EmployeeResponse> getEmployeesByIdMap();

    List<EmployeeResponse> getSortedSalaryDesc();

	List<String> getEmployeesList();

	Map<String, Long> countPerDepartment();

	Queue<EmployeeResponse> getQueueOfEmployees();

	Stack<EmployeeResponse> getStackOfEmployees();

	List<EmployeeResponse> addEmployeeList(List<EmployeeRequest> requestList);

	List<EmployeeResponse> getTopThree();

	String getDepartmentWithHighestSalary();

	List<EmployeeResponse> getEmpEarnMoreThanAvgSalary();

	Character getMostCommonFirstLetter();

	List<EmployeeResponse> getSecondHighestSalary();
}
