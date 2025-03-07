package com.employee.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePagginationReq;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeService {


	EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

	Map<String, Double> getTotalSalaryPerDepartment();

	Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

	Set<String> getUniqueEmployeeDepartments();

	Map<Long, EmployeeResponse> getEmployeesByIdMap();

	void processAndSaveEmployees(MultipartFile file);

	List<Employee> getTop3HighestPaidEmployeesInEachDepartment();

	List<Employee> getEmployeesWithSecondHighestSalary();

	String getDepartmentWithHighestTotalSalary();

	List<Employee> getEmployeesEarningMoreThanDepartmentAverage();

	String getMostCommonFirstLetterInEmployeeNames();

	Object getAllEmployee(EmployeePagginationReq employeePagginationReq);
}
