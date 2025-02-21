package com.employee.demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService service) {
		this.employeeService = service;
	}

	@PostMapping("/add")
	public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
		return employeeService.addEmployee(employeeRequest);
	}

	@GetMapping("/grouped-by-department")
	public Map<String, List<Employee>> getAllEmployeeByDepartment() {
		return employeeService.getAllEmployeeByDepartment();
	}

	@GetMapping("/total-salary")
	public double getTotalOfSalary() {
		return employeeService.findTotalOfSalary();
	}

	@GetMapping("/unique-department")
	public List<String> getUniqueDepartment() {
		return employeeService.findUniqueDepartment();
	}

	@GetMapping("/sorted-by-salary")
	List<Employee> getEmployeeSortedBySalary() {
		return employeeService.findEmployeeSortedBySalary();
	}

	@GetMapping("/employee-names")
	public List<String> getAllEmployeeName() {
		return employeeService.findAllEmployeeName();
	}

	@GetMapping("/count-per-department")
	public Map<String, Integer> getTotalEmployeeCountByEachDepartment() {
		return employeeService.findTotalEmployeeCountByEachDepartment();
	}

	@GetMapping("/employee-map")
	Map<Integer, Employee> getAllEmployeeDataOfId() {
		return employeeService.getAllEmployeeDataOnId();
	}

	@GetMapping("/stack")
	List<Employee> getLatestWiseEmployees() {
		return employeeService.findLatestWiseEmployees();
	}

	@GetMapping("/queue")
	List<Employee> getEarliestWiseEmployees() {
		return employeeService.findEarliestWiseEmployees();
	}

	@PostMapping("/add-multiple")
	List<Employee> addMultipleEmployee(@RequestBody List<Employee> employees) {
		return employeeService.addMultipleEmployee(employees);
	}

}