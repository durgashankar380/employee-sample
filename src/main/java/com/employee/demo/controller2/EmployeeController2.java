package com.employee.demo.controller2;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service2.EmployeeService2;
import org.apache.naming.EjbRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employeeSQL")
public class EmployeeController2 {
    @Autowired
    public EmployeeService2 service2;

    @GetMapping("/getAll")
    public List<EmployeeResponse> getAll(){
        return service2.getAll();
    }

    @PostMapping("/add")
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        System.out.println(employeeRequest);
        return service2.addEmployee(employeeRequest);
    }

    @GetMapping("/total-salary")
    public Map<String, Double> getTotalSalaryPerDepartment() {
        return service2.getTotalSalaryPerDepartment();
    }

    @GetMapping("/grouped-by-department")
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        return service2.getEmployeesGroupedByDepartment();
    }

    @GetMapping("/unique-departments")
    public Set<String> getUniqueEmployeeDepartments() {
        return service2.getUniqueEmployeeDepartments();
    }

    @GetMapping("/employee-map")
    public Map<Long, EmployeeResponse> getEmployeesByIdMap() {
        return service2.getEmployeesByIdMap();
    }

    @GetMapping("/sorted-by-salary")
    public List<EmployeeResponse> getSortedSalary(){
        return service2.getSortedSalaryDesc();
    }

    @GetMapping("/employee-names")
    public List<String> getEmployeesList(){
        return service2.getEmployeesList();
    }

    @GetMapping("/count-per-department")
    public Map<String,Long> countPerDepartment(){
        return service2.countPerDepartment();
    }


    @PostMapping("/add-multiple")
    public List<EmployeeResponse> addMultiple(@RequestBody List<EmployeeRequest> requestList){
        return service2.addEmployeeList(requestList);
    }
    @GetMapping("/topThreeHighestSalary")
    public List<Employee> topThree(){
        return service2.getTopThree();
    }

    @GetMapping("/secondHighestSalary")
    public List<EmployeeResponse> secondHighestSalary(){
        return service2.getSecondHighestSalary();
    }

    @GetMapping("/departmentWithHighestSalary")
    public String departmentWithHighestSalary(){
        return service2.getDepartmentWithHighestSalary();
    }

    @GetMapping("/empEarnMoreThanAvgSalary")
    public List<EmployeeResponse> empEarnMoreThanAvgSalary(){
        return service2.getEmpEarnMoreThanAvgSalary();
    }

    @GetMapping("/mostCommonFirstLetter")
    public Character mostCommonFirstLetter(){
        return service2.getMostCommonFirstLetter();
    }
}
