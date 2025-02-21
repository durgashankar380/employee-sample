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

    /**
     * @return
     * @autor
     */

    @GetMapping("/stackOfEmployee")
    public List<Employee>stackOfEmployee()
    {
        return this.service.stackOfEmployee();
    }



//    @GetMapping("/queueOfEmployee")
//    public List<Employee>queueOfEmployee()
//    {
//        return this.service.queueOfEmployee();
//    }


    @PostMapping("/add-multiple")
    public ResponseEntity<Employee> addMultipleEmployees(@RequestBody List<Employee> employeeEntity) {
        return service.addMultipleEmployees(employeeEntity);
    }


    @GetMapping("/count-per-department")
    public Map<String, Long> getcountperDepartment() {
        return service.getcountperDepartment();
    }

    @GetMapping("/sorted-by-salary")
    public List<EmployeeResponse> getEmployeesSortedBySalary() {
        return service.getAllEmployees();
    }


    @GetMapping("/employee-names")
    public List<String> getEmployeeNames() {
        return service.getAllEmployeeName();
    }


    @GetMapping("/getAllEmployees")
    public List<EmployeeResponse> getAllEmployees() {
        return this.service.getAllEmployees();
    }

    @GetMapping("/getEmployeeById/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id) {
        return this.service.getEmployeeById(id);
    }

    @GetMapping("/getEmployeeByName")
    public Employee getEmployeeByName(@RequestParam String name) {
        return this.service.getEmployeeByName(name);
    }

    @PutMapping("/updateEmpById/{id}")
    public Employee updateEmpById(@PathVariable long id, @RequestBody Employee employeeEntity) {
        return this.service.updateEmpById(id, employeeEntity);
    }

    @GetMapping("/getAllEmployeeByDepartment")
    public List<Employee> getAllEmployeeByDepartment(@RequestParam String department) {
        return this.service.getAllEmployeeByDepartment(department);
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody EmployeeRequest employee) {
        return this.service.addEmployee(employee);
    }

    @GetMapping("/findTotalSalaryByDepartment")
    public Map<String, Double> getTotalSalaryByDepartment() {
        return  service.getTotalSalaryByDepartment();
    }

    @GetMapping("/getbydeptsalary/{dept}")
    public ResponseEntity<?> depSalary(@PathVariable String dept){
        return service.totalSalaryByDepartment(dept);
    }




}
