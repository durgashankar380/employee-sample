package com.employee.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeePagginationReq;
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

   /**
    * 
    * @param employeeRequest
    * @return
    */

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
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadEmployeeData(@RequestParam("file") MultipartFile file) {
        service.processAndSaveEmployees(file);
		return ResponseEntity.ok("File uploaded and processed successfully.");
    }
    
    // 1. Get the Top 3 Highest Paid Employees in Each Department
    @GetMapping("/top3-salaries-per-department")
    public List<Employee> getTop3HighestPaidEmployeesInEachDepartment() {
        return service.getTop3HighestPaidEmployeesInEachDepartment();
    }

    // 2. Get Employees with the Second-Highest Salary
    @GetMapping("/second-highest-salary")
    public List<Employee> getEmployeesWithSecondHighestSalary() {
        return service.getEmployeesWithSecondHighestSalary();
    }

    // 3. Get Department with the Highest Total Salary
    @GetMapping("/highest-total-salary-department")
    public String getDepartmentWithHighestTotalSalary() {
        return service.getDepartmentWithHighestTotalSalary();
    }

    // 4. Get Employees Who Earn More Than Their Department's Average Salary
    @GetMapping("/above-department-average")
    public List<Employee> getEmployeesEarningMoreThanDepartmentAverage() {
        return service.getEmployeesEarningMoreThanDepartmentAverage();
    }

    // 5. Get Most Common First Letter in Employee Names
    @GetMapping("/most-common-first-letter")
    public String getMostCommonFirstLetterInEmployeeNames() {
        return service.getMostCommonFirstLetterInEmployeeNames();
    }
    
    @PostMapping("/list")
    public ResponseEntity<Object> getAllEmployee(@RequestBody EmployeePagginationReq employeePagginationReq)
            throws Exception {

        return new ResponseEntity<>(service.getAllEmployee(employeePagginationReq), HttpStatus.OK);
    }
}