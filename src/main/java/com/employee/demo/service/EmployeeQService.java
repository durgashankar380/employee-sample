package com.employee.demo.service;

import java.util.List;
import java.util.Map;

import com.employee.demo.model.Employee;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeQService {

	Double findTotalOfSalary();

	List<String> findUniqueDepartment();

	List<Employee> findEmployeeSortedBySalary();

	List<String> findAllEmployeeName();

	List<Employee> findLatestWiseEmployees();

	List<Employee> findEarliestWiseEmployees();

	Map<Double, List<Employee>> findSecondHighestPaidEmployee();

	Map<Integer, Employee> getAllEmployeeDataOnId();

	Map<String, Double> findHighestPaidDepartment();

	Map<String, Long> findCountOfEmployeeInDepartment();

	Map<String, List<EmployeeResponse>> getAllEmployeeByDepartment();

	List<Employee> getAllEmployee();

	List<Employee> getAll();

}
