package com.employee.demo.controller;

import com.employee.demo.model.Employee;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("employee-Query")
public class EmployeeController2 {

    @Autowired
    EmployeeService2 service2;

    @PostMapping("/add-Employee")
    public void addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        this.service2.addEmployee(employeeRequest);
    }

    @GetMapping("/get-AllEmployee")
    public List<Employee> getAllEmployee() {
        return this.service2.getAllEmployee();
    }

    @GetMapping("/unique_departments")
    public Set<String> getUniqueEmployeeDepartments() {
        return service2.getUniqueEmployeeDepartments();
    }


    @GetMapping("/employee_names")
    public List<String> getEmployeeNames() {
        return service2.getAllEmployeeName();
    }


    @GetMapping("/sorted-Bysalary")
    public List<Employee> getEmployeesSortedBySalary() {
        return service2.getEmployeesSortedBySalary();
    }

    @GetMapping("/queue-Employee")
    public List<Employee> queueOfEmployee() {
        return this.service2.queueOfEmployee();
    }

    @GetMapping("/stack-Of-Employee")
    public List<Employee> stackOfEmployee() {
        return this.service2.stackOfEmployee();
    }

    @GetMapping("/top3")
    public List<Employee> getTop3() {
        return this.service2.getTopThree();
    }


    @GetMapping("/findTotalSalaryByDepartment")
    public List<Object> getTotalSalaryByDepartment() {
        return service2.getTotalSalaryByDepartment();
    }

    @GetMapping("/department-withHighest-salary")
    String findDepartmentWithHighestTotalSalary() {
        return service2.findDepartmentWithHighestTotalSalary();
    }

    @GetMapping("/avg-salary")
    List<Employee> findEmployeesAboveDepartmentAverageSalary() {
        return service2.findEmployeesAboveDepartmentAverageSalary();
    }

    @GetMapping("/most-common-firstletter")
    List<Object[]> findMostCommonFirstLetter() {
        return service2.findMostCommonFirstLetter();
    }

    @GetMapping("/2HighestSalary")
    List<Employee> findEmployeesWithSecondHighestSalary() {
        return service2.findEmployeesWithSecondHighestSalary();
    }

    @GetMapping("get-emp-grp-by-dep")
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        return service2.getEmployeesGroupedByDepartment();
    }

    @GetMapping("/emp-by-id")
    public Map<Long, EmployeeResponse> getEmployeeById(@RequestParam("id") Long id) { // Added parameter
        return service2.getEmployeeById(id);
    }

    @GetMapping("/count-per-department")
    public Map<String, Long> getcountperDepartment() {
        return service2.getcountperDepartment();
    }

    @PutMapping("/update-Emp-By-Id/{id}")
    public Employee updateEmpById(@PathVariable long id, @RequestBody Employee employeeEntity) {
        return this.service2.updateEmpById(id, employeeEntity);

    }
}
