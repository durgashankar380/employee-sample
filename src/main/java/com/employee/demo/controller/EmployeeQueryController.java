package com.employee.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeQueryService;

@RestController
@RequestMapping("/employee-query")
public class EmployeeQueryController {

	private EmployeeQueryService employeeQueryService;

	public EmployeeQueryController(EmployeeQueryService service) {
		this.employeeQueryService = service;
	}

	@GetMapping("/total-salary")
	public Double getTotalOfSalary() {
		return employeeQueryService.findTotalOfSalary();
	}

	@GetMapping("/unique-department")
	public List<String> getUniqueDepartment() {
		return employeeQueryService.findUniqueDepartment();
	}

	@GetMapping("/sorted-by-salary")
	List<Employee> getEmployeeSortedBySalary() {
		return employeeQueryService.findEmployeeSortedBySalary();
	}

	@GetMapping("/employee-names")
	public List<String> getAllEmployeeName() {
		return employeeQueryService.findAllEmployeeName();
	}

	@GetMapping("/stack")
	List<Employee> getLatestWiseEmployees() {
		return employeeQueryService.findLatestWiseEmployees();
	}

	@GetMapping("/queue")
	List<Employee> getEarliestWiseEmployees() {
		return employeeQueryService.findEarliestWiseEmployees();
	}

	@GetMapping("/second-highest-salary")
	Map<Double, List<Employee>> getSecondHighestPaidEmployees() {
		return employeeQueryService.findSecondHighestPaidEmployee();
	}

	@GetMapping("/employee-map")
	Map<Integer, Employee> getAllEmployeeDataOfId() {
		return employeeQueryService.getAllEmployeeDataOnId();
	}

	@GetMapping("/count-per-department")
	public Map<String, Long> getTotalEmployeeCountByEachDepartment() {
		return employeeQueryService.findCountOfEmployeeInDepartment();
	}

	@GetMapping("/highest-paid-department")
	Map<String, Double> getHighestSalariedDepartment() {
		return employeeQueryService.findHighestPaidDepartment();
	}
	
	@GetMapping("/grouped-by-department")
	public Map<String, List<EmployeeResponse>> getAllEmployeeByDepartment() {
		return employeeQueryService.getAllEmployeeByDepartment();
	}

}
