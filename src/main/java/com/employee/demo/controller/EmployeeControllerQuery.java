package com.employee.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeServiceQuery;

@RestController
@RequestMapping("/emp")
public class EmployeeControllerQuery {
	private final EmployeeServiceQuery service;

	public EmployeeControllerQuery(EmployeeServiceQuery service) {
		this.service = service;
	}
	
	
	@GetMapping("/total-salary")
	public Map<String, Double> getTotalSalaryPerDepartment() {
		return service.getTotalSalaryPerDepartment();		
	}
	
	@GetMapping("/unique-departments")
	public List<String> getUniqueEmployeeDepartments(){
		return service.getUniqueEmployeeDepartments();
	}
	
	@GetMapping("/grouped-by-department")
	public Map<String, List<EmployeeResponse>> getEmployeeGroupByDepartment() {
		return service.getEmployeeGroupByDepartment();
	}
	
	@GetMapping("/employee-map")
	public Map<Long, EmployeeResponse> getEmployeeByIdMap() {
		return service.getEmployeeByIdMap();	
	}
	
	@GetMapping("/sort-by-salary")
	public List<Employee> getEmployeeSortBySalary() {
		return service.getEmployeeSortBySalary();
	}
	
	@GetMapping("/count-per-department")
	public Map<String, Long> getDepartmentEmployeeCount() {
		return service.getDepartmentEmployeeCount();
	}
	
	@GetMapping("/employee-name")
	public List<String> getAllEmployeeName() {
		return service.getAllEmployeeName();
	}
	
	@GetMapping("/queue")
	public List<Employee> getEmployeeFirstInFirstOut() {
		return service.getEmployeeFirstInFirstOut();
	}
	
	@GetMapping("/stack")
	public List<Employee> getEmployeeLastInFirstOut() {
		return service.getEmployeeLastInFirstOut();
	}
	
	@GetMapping("/second-highest-salary")
	public List<Employee> getSecondHighestSalary() {
		return service.getSecondHighestSalary();
	}
	
	@GetMapping("/Top-3-Highest-Paid-Employee")
	public Map<String, List<EmployeeResponse>> getTop3HighestPaidEmployeePerDepartment() {
		return service.getTop3HighestPaidEmployeePerDepartment();	
	}
	
	@GetMapping("/department-average-salary")
	public List<Object[]> getEmployeeEarnAboveDepartmentAverageSalary() {
		return service.getEmployeeEarnAboveDepartmentAverageSalary();
	}
	
	@GetMapping("/highest-total-salary-department")
    public List<String> getDepartmentWithHighestTotalSalary() {
		return service.getDepartmentWithHighestTotalSalary();
    }
	
	@GetMapping("/most-common-first-letter")
    public List<String> mostCommonFirstLetterinEmployeeNames() {
		return service.mostCommonFirstLetterinEmployeeNames();
    }
}
