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
	private final EmployeeServiceQuery service1;

	public EmployeeControllerQuery(EmployeeServiceQuery service1) {
		this.service1 = service1;
	}
	
	@GetMapping("/total-salary-1")
	public Map<String, Double> getTotalSalaryPerDepartment() {
		return service1.getTotalSalaryPerDepartment();		
	}
	
	@GetMapping("/unique-departments-1")
	public List<String> getUniqueEmployeeDepartments(){
		return service1.getUniqueEmployeeDepartments();
	}
	
	@GetMapping("/grouped-by-department-1")
	public Map<String, List<EmployeeResponse>> getEmployeeGroupByDepartment() {
		return service1.getEmployeeGroupByDepartment();
	}
	
	@GetMapping("/employee-map-1")
	public Map<Long, EmployeeResponse> getEmployeeByIdMap() {
		return service1.getEmployeeByIdMap();	
	}
	
	@GetMapping("/sort-by-salary-1")
	public List<Employee> getEmployeeSortBySalary() {
		return service1.getEmployeeSortBySalary();
	}
	
	@GetMapping("/count-per-department-1")
	public Map<String, Long> getDepartmentEmployeeCount() {
		return service1.getDepartmentEmployeeCount();
	}
	
	@GetMapping("/employee-name")
	public List<String> getAllEmployeeName() {
		return service1.getAllEmployeeName();
	}
	
	@GetMapping("/queue-1")
	public List<Employee> getEmployeeFirstInFirstOut() {
		return service1.getEmployeeFirstInFirstOut();
	}
	
	@GetMapping("/stack-1")
	public List<Employee> getEmployeeLastInFirstOut() {
		return service1.getEmployeeLastInFirstOut();
	}
	
	@GetMapping("/second-highest-salary-1")
	public List<Employee> getSecondHighestSalary() {
		return service1.getSecondHighestSalary();
	}
	
	@GetMapping("/Top-3-Highest-Paid-Employee-1")
	public Map<String, List<EmployeeResponse>> getTop3HighestPaidEmployeePerDepartment() {
		return service1.getTop3HighestPaidEmployeePerDepartment();	
	}
	
	@GetMapping("/department-average-salary-1")
	public List<Object[]> getEmployeeEarnAboveDepartmentAverageSalary() {
		return service1.getEmployeeEarnAboveDepartmentAverageSalary();
	}
	
	@GetMapping("/highest-total-salary-department-1")
    public List<String> getDepartmentWithHighestTotalSalary() {
		return service1.getDepartmentWithHighestTotalSalary();
    }
	
	@GetMapping("/most-common-first-letter-1")
    public List<String> mostCommonFirstLetterinEmployeeNames() {
		return service1.mostCommonFirstLetterinEmployeeNames();
    }
}
