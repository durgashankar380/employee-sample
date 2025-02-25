package com.employee.demo.controller;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<EmployeeResponse> getSortedSalary(){
        return service.getSortedSalaryDesc();
    }

    @GetMapping("/employee-names")
    public List<String> getEmployeesList(){
        return service.getEmployeesList();
    }

    @GetMapping("/count-per-department")
    public Map<String,Long> countPerDepartment(){
        return service.countPerDepartment();
    }

    @GetMapping("/queue")
    public Queue<EmployeeResponse> getQueue(){
        return service.getQueueOfEmployees();
    }

    @GetMapping("/stack")
    public Stack<EmployeeResponse> getStack(){
        return service.getStackOfEmployees();
    }

    @PostMapping("/add-multiple")
    public List<EmployeeResponse> addMultiple(@RequestBody List<EmployeeRequest> requestList){
        return service.addEmployeeList(requestList);
    }
    @GetMapping("/top3")
    public List<EmployeeResponse> topThree(){
        return service.getTopThree();
    }

    @GetMapping("/secondHighestSalary")
    public List<EmployeeResponse> secondHighestSalary(){
        return service.getSecondHighestSalary();
    }
    @GetMapping("/departmentWithHighestSalary")
    public String departmentWithHighestSalary(){
        return service.getDepartmentWithHighestSalary();
    }
    @GetMapping("/empEarnMoreThanAvgSalary")
    public List<EmployeeResponse> empEarnMoreThanAvgSalary(){
        return service.getEmpEarnMoreThanAvgSalary();
    }
    @GetMapping("/mostCommonFirstLetter")
    public Character mostCommonFirstLetter(){
        return service.getMostCommonFirstLetter();
    }

//    Find the Top 3 Highest Paid Employees in Each Department
//    Find Employees with the Second-Highest Salary
//    Find the Department with the Highest Total Salary
//    Find Employees Who Earn More Than Their Department's Average Salary
//    Find the Most Common First Letter in Employee Names

}