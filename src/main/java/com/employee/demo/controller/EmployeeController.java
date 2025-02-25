package com.employee.demo.controller;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping("/addEmployee")
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.service.addEmployee(employeeRequest);
    }

    @GetMapping("/findTotalSalaryByDepartment")
    public Map<String, Double> getTotalSalaryByDepartment() {
        return  service.getTotalSalaryByDepartment();
    }

    @GetMapping("/grouped-by-department")
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        return service.getEmployeesGroupedByDepartment();
    }

    @GetMapping("/unique-departments")
    public Set<String> getUniqueEmployeeDepartments(){
        return service.getUniqueEmployeeDepartments();
    }

    @GetMapping("/getEmployeeById")
    public Map<Long ,EmployeeResponse> getEmployeeById() {
        return this.service.getEmployeeById();
    }

    @GetMapping("/employee-names")
    public List<String> getEmployeeNames() {
        return service.getAllEmployeeName();
    }

    @GetMapping("/queue")
    public Queue<EmployeeResponse>queueOfEmployee()
    {
        return this.service.queueOfEmployee();
    }

    @GetMapping("/stackOfEmployee")
    public Stack<EmployeeResponse>stackOfEmployee()
    {
        return this.service.stackOfEmployee();
    }

    @PostMapping("/add-multiple")
    public List<EmployeeResponse> addMultipleEmployees(List<EmployeeRequest> employeeRequests)  {
        return service.addMultipleEmployees(employeeRequests);
    }


    @GetMapping("/count-per-department")
    public Map<String, Long> getcountperDepartment() {
        return service.getcountperDepartment();
    }

    @GetMapping("/sorted-by-salary")
    public List<EmployeeResponse> getEmployeesSortedBySalary() {
        return service.getEmployeesSortedBySalary();
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeResponse> getAllEmployees() {
        return this.service.getEmployeesSortedBySalary();
    }

    @GetMapping("/getEmployeeByName")
    public Employee getEmployeeByName(@RequestParam String name) {
        return this.service.getEmployeeByName(name);
    }

    @PutMapping("/updateEmpById/{id}")
    public Employee updateEmpById(@PathVariable long id, @RequestBody Employee employeeEntity) {
        return this.service.updateEmpById(id, employeeEntity);
    }

    @GetMapping("/getbydeptsalary/{dept}")
    public ResponseEntity<?> depSalary(@PathVariable String dept){
        return service.totalSalaryByDepartment(dept);
    }

   @GetMapping("/highestPaidEmployee")
    public List<EmployeeResponse> thirdhighestPaidEmployee(){
        return this.service.thirdhighestPaidEmployee();
   }

    @GetMapping("/secondHighestSalary")
    public List<EmployeeResponse> getEmployeesWithSecondHighestSalary()
    {
        return this.service.getEmployeesWithSecondHighestSalary();
    }

    @GetMapping("/departmentWithHighestTotalSalary")
    public String getDepartmentWithHighestTotalSalary(){

        return this.service.getDepartmentWithHighestTotalSalary();
    }

    @GetMapping("/avgSalary")
    public List<Employee> getAverageSalary()
    {
        return this.service.getAverageSalary();
    }


    @GetMapping("/commonletter")
    public Character getMostCommonFirstLetter()
    {
        return this.service.getMostCommonFirstLetter();
    }



}
