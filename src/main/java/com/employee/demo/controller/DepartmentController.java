package com.employee.demo.controller;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentRequestWithEmployeeList;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    private Long id;

    @GetMapping("/get_departments")
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("get_by_id/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        this.id = id;
        return departmentService.getDepartmentById(id);
    }
    @GetMapping("get_by_name/{name}")
    public ResponseEntity<?> getDepartmentByName(@PathVariable String name) {
        return departmentService.getDepartmentByName(name);
    }

    @GetMapping("/total-salary")
    public Map<String, Double> getTotalSalaryPerDepartment() {
        return departmentService.getTotalSalaryPerDepartment();
    }

    @GetMapping("/grouped-by-department")
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        return departmentService.getEmployeesGroupedByDepartment();
    }
    @GetMapping("/departmentWithHighestSalary")
    public String departmentWithHighestSalary(){
        return departmentService.getDepartmentWithHighestSalary();
    }

    @GetMapping("/unique-departments")
    public Set<String> getUniqueEmployeeDepartments() {
        return departmentService.getUniqueEmployeeDepartments();
    }
        @GetMapping("/empEarnMoreThanAvgSalary")
    public List<EmployeeResponse> empEarnMoreThanAvgSalary(){
        return departmentService.getEmpEarnMoreThanAvgSalary();
    }
    @GetMapping("/count-per-department")
    public Map<String,Long> countPerDepartment(){
        return departmentService.countPerDepartment();
    }


    @DeleteMapping("delete/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PostMapping("/add_and_update_employeeList")
    public ResponseEntity<?> addAndUpdateEmployeeListByDepartment(@RequestBody DepartmentRequestWithEmployeeList requests){
        return departmentService.addAndUpdateEmployeeListByDepartment(requests);
    }
}
