package com.employee.demo.controller;

import java.util.*;

import com.employee.demo.request.JwtResetRequest;
import com.employee.demo.service.DepartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    private DepartmentService departmentService;
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

   

    @PostMapping("/addAndUpdate")
    public ResponseEntity<?> addAndUpdateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return service.addAndUpdateEmployee(employeeRequest);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> resetPassword(@RequestBody JwtResetRequest resetRequest){
        return service.resetPassword(resetRequest);
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


    @GetMapping("/mostCommonFirstLetter")
    public Character mostCommonFirstLetter(){
        return service.getMostCommonFirstLetter();
    }

//    Find the Top 3 Highest Paid Employees in Each Department
//    Find Employees with the Second-Highest Salary
//    Find the Department with the Highest Total Salary
//    Find Employees Who Earn More Than Their Department's Average Salary
//    Find the Most Common First Letter in Employee Names

    @DeleteMapping("/delete_employee")
    public void deleteEmployee(@RequestParam Long id){
        service.deleteEmployee(id);
    }
}