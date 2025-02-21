package com.employee.demo.service;

import java.util.List;
import java.util.Map;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeService {

	EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

	Map<String, List<Employee>> getAllEmployeeByDepartment();

	double findTotalOfSalary();

	List<String> findUniqueDepartment();

	List<Employee> findEmployeeSortedBySalary();

	List<String> findAllEmployeeName();

	Map<String, Integer> findTotalEmployeeCountByEachDepartment();

	Map<Integer, Employee> getAllEmployeeDataOnId();

	List<Employee> findLatestWiseEmployees();

	List<Employee> findEarliestWiseEmployees();

	List<Employee> addMultipleEmployee(List<Employee> employees);

}
