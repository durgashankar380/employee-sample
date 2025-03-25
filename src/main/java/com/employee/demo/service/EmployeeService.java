package com.employee.demo.service;

import java.util.*;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.JwtForgetRequest;
import com.employee.demo.request.JwtResetRequest;
import com.employee.demo.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {


	ResponseEntity<?> addAndUpdateEmployee(EmployeeRequest employeeRequest);

	Map<String, Double> getTotalSalaryPerDepartment();

//	Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

//	Set<String> getUniqueEmployeeDepartments();

	Map<Long, EmployeeResponse> getEmployeesByIdMap();

    List<EmployeeResponse> getSortedSalaryDesc();

	List<String> getEmployeesList();

//	Map<String, Long> countPerDepartment();

	Queue<EmployeeResponse> getQueueOfEmployees();

	Stack<EmployeeResponse> getStackOfEmployees();

	List<EmployeeResponse> addEmployeeList(List<EmployeeRequest> requestList);

	List<EmployeeResponse> getTopThree();

//	String getDepartmentWithHighestSalary();

//	List<EmployeeResponse> getEmpEarnMoreThanAvgSalary();

	Character getMostCommonFirstLetter();

	List<EmployeeResponse> getSecondHighestSalary();

	ResponseEntity<?> registerEmployee(EmployeeRequest employeeRequest);

	void setLastLogin( String email);

	ResponseEntity<?> forgetPassword(JwtForgetRequest request);

	ResponseEntity<?> resetPassword(JwtResetRequest resetRequest);

	void deleteEmployee(Long id);

}
