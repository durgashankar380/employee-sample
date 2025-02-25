package com.employee.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
    public List<EmployeeResponse> getEmployeesSortedBySalary() {
        return service.getEmployeesSortedBySalary();
    }

    @GetMapping("/employee-names")
    public List<String> getEmployeeNames() {
        return service.getEmployeeNames();
    }

    @GetMapping("/count-per-department")
    public Map<String, Long> getEmployeeCountPerDepartment() {
        return service.getEmployeeCountPerDepartment();
    }

    @GetMapping("/queue")
    public Queue<EmployeeResponse> getEmployeesAsQueue() {
        return service.getEmployeesAsQueue();
    }

    @GetMapping("/stack")
    public Stack<EmployeeResponse> getEmployeesAsStack() {
        return service.getEmployeesAsStack();
    }
    
    @PostMapping("/add-multiple")
    public List<EmployeeResponse> addMultipleEmployees(@RequestBody List<EmployeeRequest> employeeRequests) {
        return service.addMultipleEmployees(employeeRequests);
    }

}