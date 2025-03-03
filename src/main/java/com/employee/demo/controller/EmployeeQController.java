package com.employee.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeQService;

@RestController
@RequestMapping("/employeeQ")
public class EmployeeQController {

	private EmployeeQService employeeQService;

	public EmployeeQController(EmployeeQService service) {
		this.employeeQService = service;
	}

	@GetMapping("/total-salary")
	public Double getTotalOfSalary() {
		return employeeQService.findTotalOfSalary();
	}

	@GetMapping("/unique-department")
	public List<String> getUniqueDepartment() {
		return employeeQService.findUniqueDepartment();
	}

	@GetMapping("/sorted-by-salary")
	List<Employee> getEmployeeSortedBySalary() {
		return employeeQService.findEmployeeSortedBySalary();
	}

	@GetMapping("/employee-names")
	public List<String> getAllEmployeeName() {
		return employeeQService.findAllEmployeeName();
	}

	@GetMapping("/stack")
	List<Employee> getLatestWiseEmployees() {
		return employeeQService.findLatestWiseEmployees();
	}

	@GetMapping("/queue")
	List<Employee> getEarliestWiseEmployees() {
		return employeeQService.findEarliestWiseEmployees();
	}

	@GetMapping("/second-highest-salary")
	Map<Double, List<Employee>> getSecondHighestPaidEmployees() {
		return employeeQService.findSecondHighestPaidEmployee();
	}

	@GetMapping("/employee-map")
	Map<Integer, Employee> getAllEmployeeDataOfId() {
		return employeeQService.getAllEmployeeDataOnId();
	}

	@GetMapping("/count-per-department")
	public Map<String, Long> getTotalEmployeeCountByEachDepartment() {
		return employeeQService.findCountOfEmployeeInDepartment();
	}

	@GetMapping("/highest-paid-department")
	Map<String, Double> getHighestSalariedDepartment() {
		return employeeQService.findHighestPaidDepartment();
	}
	
	@GetMapping("/grouped-by-department")
	public Map<String, List<EmployeeResponse>> getAllEmployeeByDepartment() {
		return employeeQService.getAllEmployeeByDepartment();
	}

}
