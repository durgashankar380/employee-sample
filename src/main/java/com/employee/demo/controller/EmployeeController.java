package com.employee.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.response.ResponseEmployee;
import com.employee.demo.service.EmployeeService;


@RestController  
@RequestMapping("/employees")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
     
    
    //1
    @PostMapping("/add") 
    public Employee createEmployee(@RequestBody RequestEmployee requestEmployee) {
        return employeeService.saveEmployee(requestEmployee);
    }
    
    //2
    @GetMapping("/total-Salary")
    public Map<String, Double> getTotalSalaryPerDepartment() {
    	return employeeService.getTotalSalaryPerDepartment();
    }
    

    //3
    @GetMapping("/grouped-by-department")
    public Map<String, List<Employee>> getEmployeesGroupedByDepartment() {
    	return employeeService.getEmployeesGroupedByDepartment();
    }
    
    
    //4
    @GetMapping("/unique-departments")
    public ResponseEntity<Set<String>> getUniqueDepartments() {
    	return ResponseEntity.ok(employeeService.getUniqueDepartments());
    }
    
    //5
    @GetMapping("/map")
    public Map<Long, Employee> getEmployeesAsMap() {
    	return employeeService.getEmployeesAsMap();
    }
    
    //6
    @PostMapping("/add-multiple")
    public List<Employee> createEmployees(@RequestBody List<RequestEmployee> requestEmployees) {
    	return employeeService.saveEmployees(requestEmployees);
    }
    
    
    //7
    @GetMapping("/sorted-by-salary")
    public List<Employee> getEmployeesSortedBySalaryDesc() {
        return employeeService.getEmployeesSortedBySalaryDesc();
    }
    
    //8
    @GetMapping("/names")
    public List<String> getEmployeeNames() {
        return employeeService.getEmployeeNames();
    }
    
    //9
    @GetMapping("/count-by-department")
    public Map<String, Integer> getStudentCountByDepartment() {
        return employeeService.getStudentCountByDepartment();
    }
    
    //10
    @GetMapping("/fifo")
    public List<Employee> getEmployeesInFIFOOrder() {
        return employeeService.getEmployeesInFIFOOrder();
    }
    
    //11
    @GetMapping("/lifo-employees")
    public List<Employee> getEmployeesInLIFOOrder() {
        return employeeService.getEmployeesInLIFOOrder();
    }
    
    
    //12
    @GetMapping("/top-three-employees")
    public Map<String, List<ResponseEmployee>> getTop3EmployeesByDepartment() {
    	return  employeeService. getTop3EmployeesByDepartment();
    }
    
    //13
    @GetMapping("/second-highest")
    public List<ResponseEmployee> getEmployeesWithSecondHighestGrade(){
    	return  employeeService.getEmployeesWithSecondHighestGrade();
    }
    
    //14
    @GetMapping("/highest-total-grade")
    public String getDepartmentWithHighestTotalGrade() {
    	return  employeeService.getDepartmentWithHighestTotalGrade();
    }

    //15
    @GetMapping("/more-then-department-average")
    public List<ResponseEmployee> getEmployeesAboveDepartmentAverage(){
    	return employeeService.getEmployeesAboveDepartmentAverage();
    }
    
    //16
    @GetMapping("/most-comman-first-letter")
    public char getMostCommonFirstLetter() {
		return employeeService.getMostCommonFirstLetter();
	}
    
    //17
     @GetMapping("/Employee-by-id")  
    public ResponseEmployee getEmployeeById(@RequestParam Long id) {
        return employeeService.getEmployeeById(id);
    }
    
    
    
  //18
   @PutMapping("/Update")  
    public Employee updateEmployee(@RequestParam Long id, @RequestBody RequestEmployee requestEmployee) {
        return employeeService.updateEmployee(id, requestEmployee);
    }

    
    //19
    @DeleteMapping("/delete")  
    public String deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully!";
   }
   
    
     } 
    

