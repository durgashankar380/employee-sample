package com.employee.demo.service;

import java.util.List;
import java.util.Map;

import com.employee.demo.model.Employee;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeServiceQuery {

	Map<String, Double> getTotalSalaryPerDepartment();

	List<String> getUniqueEmployeeDepartments();

	Map<String, List<EmployeeResponse>> getEmployeeGroupByDepartment();
	
	Map<Long, EmployeeResponse> getEmployeeByIdMap();

	List<Employee> getEmployeeSortBySalary();

	Map<String, Long> getDepartmentEmployeeCount();

	List<String> getAllEmployeeName();

	List<Employee> getEmployeeFirstInFirstOut();

	List<Employee> getEmployeeLastInFirstOut();

	List<Employee> getSecondHighestSalary();

	List<Object[]> getEmployeeEarnAboveDepartmentAverageSalary();

	List<String> getDepartmentWithHighestTotalSalary();

	List<String> mostCommonFirstLetterinEmployeeNames();

	Map<String, List<EmployeeResponse>> getTop3HighestPaidEmployeePerDepartment();


}
