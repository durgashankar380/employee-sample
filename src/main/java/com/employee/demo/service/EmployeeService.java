package com.employee.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeService {


	EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

	Map<String, Double> getTotalSalaryPerDepartment();

	Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

	Set<String> getUniqueEmployeeDepartments();

	Map<Long, EmployeeResponse> getEmployeesByIdMap();

	List<EmployeeResponse> getEmployeesSortedBySalary();

	List<String> getEmployeeNames();

	Map<String, Long> getEmployeeCountPerDepartment();

	Queue<EmployeeResponse> getEmployeesAsQueue();

	Stack<EmployeeResponse> getEmployeesAsStack();

	List<EmployeeResponse> addMultipleEmployees(List<EmployeeRequest> employeeRequests);
}
