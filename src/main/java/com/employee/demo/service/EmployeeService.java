package com.employee.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeService {

	String createEmployee(EmployeeRequest request, Integer departmentId);

	Department getEmployeeByDepartment(Integer departmentId);

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

	Map<String, List<EmployeeResponse>> findTopThreeHighestPaidEmployee();

	Map<Double, List<EmployeeResponse>> findSecondHighestPaidEmployees();

	char findMostCommonFirstWord();

	void saveFile(MultipartFile file);

	Map<String, Double> findHighestPaidDepartment();

}
