package com.employee.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Employee;
import com.employee.demo.response.ResponseEmployee;
import com.employee.demo.service.EmployeeQService;

@RestController
@RequestMapping("/employeeQ")
public class EmployeeQController {

    private final EmployeeQService employeeQService;

    public EmployeeQController(EmployeeQService employeeQService) {
        this.employeeQService = employeeQService;
    }
    

    //1 sorted by salary----
    @GetMapping("/sorted-by-salary")
    public List<Employee> getEmployeesSortedBySalary() {
        return employeeQService.getEmployeesSortedBySalary();
    }
    
    //2 get all unique departments----
    @GetMapping("/unique-departments")
    public List<String> getUniqueDepartments() {
        return employeeQService.getUniqueDepartments();
    }
    
    //3 get all employee names----
    @GetMapping("/names")
    public List<String> getEmployeeNames() {
        return employeeQService.getEmployeeNames();
    }
    
    //4 get all employees in FIFO order----
    @GetMapping("/fifo")
    public List<Employee> getEmployeesInFIFOOrder() {
        return employeeQService.getEmployeesInFIFOOrder();
    }
    
    //5 get all employees in LIFO order----
    @GetMapping("/lifo")
    public List<Employee> getEmployeesInLIFOOrder() {
        return employeeQService.getEmployeesInLIFOOrder();
    }
    
     
     //6 get employee count per department
     @GetMapping("/employee-count-per-department")
     public  Map<String,Integer> getEmployeeCountPerDepartment() {
    	 return employeeQService.getEmployeeCountPerDepartment();
     } 
     
     //7 get employees grouped by department
     @GetMapping("/grouped-by-department")
     public  Map<String, List<Employee>> getEmployeesGroupedByDepartment(){
         return employeeQService.getEmployeesGroupedByDepartment();
     }
     
     //8 get total salary per department----
     @GetMapping("/total-salary-per-department")
     public List<Object[]> getTotalSalaryPerDepartment() {
    	 return (List<Object[]>) employeeQService.getTotalSalaryPerDepartment();
     }
     
     //9 get employees As map----
     @GetMapping("/employees-map")
     public Map<Long, Employee> getEmployeesAsMap() {
         return employeeQService.getEmployeesAsMap();
     }
     
    
     //10 get the second highest salary----
     @GetMapping("/second-highest-salary")
     public List<ResponseEmployee> getEmployeesWithSecondHighestSalary() {
    	 return employeeQService.getEmployeesWithSecondHighestSalary();
     }
     
   //11 get department with highest total salary
     @GetMapping("/highest-salary-department")
     public String getDepartmentWithHighestTotalSala() {
    	 return employeeQService.findDepartmentWithHighestTotalSalary();
     }
    
   //12 get employee who have salary greater then its department average
     @GetMapping("/above-department-average")
     public Map<String, List<Employee>> getEmployeesAboveDepartmentAverage() {
    	 return employeeQService.getEmployeesAboveDepartmentAverage();
     }
    
     //13 get top3 employee per department----
    @GetMapping("/top3-by-department")
    public Map<String, List<Employee>>  getTop3EmployeesByDepartment() {
        return employeeQService.getTop3EmployeesByDepartment();
    }
        
  //14 get most common first letter from employees----
    @GetMapping("/most-common-first-letter")
    public char getMostCommonFirstLetter() {
        return employeeQService.getMostCommonFirstLetter();
    }
    
}
