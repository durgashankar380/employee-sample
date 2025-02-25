package com.employee.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return service.addEmployee(employeeRequest);
    }
    
    @GetMapping("/total-salary")
    public Map<String, Double> getTotalSalaryPerDepartment() {
        return service.getTotalSalaryPerDepartment();
    }

    @GetMapping("/grouped-by-department")
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        return service.getEmployeesGroupedByDepartment();
    }

    @GetMapping("/unique-departments")
    public Set<String> getUniqueEmployeeDepartments() {
        return service.getUniqueEmployeeDepartments();
    }

    @GetMapping("/employee-map")
    public Map<Long, EmployeeResponse> getEmployeesByIdMap() {
        return service.getEmployeesByIdMap();
    }
    
    @GetMapping("/sorted-by-salary")
    public List<Employee> getEmpSortBySalary() {
    	return service.findAllByOrderBySalaryDesc();
    			 
    }
    
    @GetMapping("/employee-name")
    public List<String> getAllEmpNames() {
		List<String> ename = service.getAllEmpNames();
    	return ename;
    }
    
    @GetMapping("/count-per-department")
    Map<String,Integer> getDepartmentEmployeeCount(){
    	Map<String,Integer> deptCount = service.getDepartmentEmployeeCount();
    	return deptCount;
    }
    
    @GetMapping("/queue")
    public List<Employee> getEmployeeInFirstInFirstOut() {
    	return service.getEmployeeInFirstInFirstOut();
    }
    
    @GetMapping("stack")
    public List<Employee> getEmployeeInLastInFirstOut() {
    	return service.getEmployeeInLastInFirstOut();
    }
    
    @PostMapping("/add-multiple")
    public List<Employee> addMultipleEmployee(@RequestBody List<Employee> employees) {
		List<Employee> addEmp = service.addMultipleEmployee(employees);
    	return addEmp;
    }
    
    @DeleteMapping("/id/{id}")
    public boolean deleteEmployeeById(@PathVariable Long id) {
    return service.deleteEmployeeById(id);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmpById(@PathVariable Long id,@RequestBody Employee e) {
    	System.out.println(id);
    	if(id!=0 && e!= null)
    	{
    		service.updateEmployeeById( id,e);
    		return ResponseEntity.status(HttpStatus.OK).build();
    	}
    	
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/Top-3-Highest-Paid-Employee")
    public Map<String, List<Employee>> getTop3HighestPaidEmployeePerDepartment() {
		return service.getTop3HighestPaidEmployeePerDepartment();	
    }
    
    @GetMapping("/second-highest-salary")
    public List<Employee> getSecondHighestSalary() {
    	return service.getSecondHighestSalary();
    }
    
    @GetMapping("/highest-total-salary-department")
    public String getDepartmentWithHighestTotalSalary() {
		return service.getDepartmentWithHighestTotalSalary();
    }
    
    @GetMapping("/department-average-salary")
    public List<Employee> getEmployeeEarnAboveDepartmentAverageSalary() {
		return service.getEmployeeEarnAboveDepartmentAverageSalary();	
    }
    
    @GetMapping("/most-common-first-letter")
    public String mostCommonFirstLetterinEmployeeNames() {
		return service.mostCommonFirstLetterinEmployeeNames();
    	
    }
}