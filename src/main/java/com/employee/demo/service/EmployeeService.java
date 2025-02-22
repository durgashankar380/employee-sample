package com.employee.demo.service;
//
//import io.MainPackage.Employee.Entity.EmployeeEntity;
//import io.MainPackage.Employee.RequestPayload.EmployeeRequest;
//import io.MainPackage.Employee.ResponsePayload.EmployeeResponse;
import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface EmployeeService {


	EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

	Map<String,Double> getTotalSalaryByDepartment();

	 Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment();

	Set<String> getUniqueEmployeeDepartments();

	Map<Long,EmployeeResponse> getEmployeeById();

	 List<EmployeeResponse> getEmployeesSortedBySalary();

	List<String>getAllEmployeeName();

	Map<String, Long> getcountperDepartment();

	Queue<EmployeeResponse> queueOfEmployee();

	Stack<EmployeeResponse>stackOfEmployee();






	//................................//




	Employee updateEmpById(long id, Employee employeeEntity);



	Employee getEmployeeByName(String name);



	ResponseEntity totalSalaryByDepartment(String dept);

	ResponseEntity<Employee> addMultipleEmployees(List<Employee> employeeEntity);
}
