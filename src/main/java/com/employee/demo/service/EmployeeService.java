package com.employee.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeService {


	EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

	Map<String, Double> getTotalSalaryPerDepartment();

	Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

	Set<String> getUniqueEmployeeDepartments();

	Map<Long, EmployeeResponse> getEmployeesByIdMap();

	List<Employee> findAllByOrderBySalaryDesc();


	List<String> getAllEmpNames();

	Map<String, Integer> getDepartmentEmployeeCount();

	List<Employee> addMultipleEmployee(List<Employee> employees);

	boolean deleteEmployeeById(Long id);

	void updateEmployeeById(Long id, Employee e);

	List<Employee> getEmployeeInFirstInFirstOut();

	List<Employee> getEmployeeInLastInFirstOut();

	
}
