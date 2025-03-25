package com.employee.demo.service.impl;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.DepartmentRequestWithEmployeeList;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.DepartmentService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department saveDepartment(DepartmentRequest request) {
        Department department =new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setLocation(request.getLocation());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Map<String, Double> getTotalSalaryPerDepartment() {
        List<Department> departments= departmentRepository.findAll();
        System.out.println(departments);
        return Map.of();
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        List<Department> departments=departmentRepository.findAll();
        return departments.stream().collect(Collectors.toMap(Department::getName
                ,department -> department.getEmployeeList().stream().map(EmployeeResponse::new).toList()));
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments() {
        return departmentRepository.findAll().stream().map(Department::getName).collect(Collectors.toSet());
    }

    @Override
    public Map<String, Long> countPerDepartment() {
        return departmentRepository.findAll().stream().collect(Collectors.toMap(Department::getName,
                department -> (long) department.getEmployeeList().size()));
    }

    @Override
    public String getDepartmentWithHighestSalary() {
        return "";
    }

    @Override
    public ResponseEntity<?> addEmployeeListByDepartment(DepartmentRequestWithEmployeeList requests) {
        Department oldDepartment=departmentRepository.findByNameAndLocation(requests.getName(),requests.getLocation()).orElse(null);
        Department department=new Department();
        if(oldDepartment==null) {
            department.setName(requests.getName());
            department.setLocation(requests.getLocation());
            department.setDescription(requests.getDescription());
            oldDepartment = departmentRepository.save(department);
        }
        System.out.println(department);
        System.out.println(requests);
        Department finalDepartment = oldDepartment;
        List<Employee> employeeList=requests.getEmployeeRequestList().stream().map(employeeRequest -> {
            Employee employee=new Employee();
            if(employeeRepository.findByEmail(employeeRequest.getEmail()).isEmpty()) {
                employee.setName(employeeRequest.getName());
                employee.setDepartment(finalDepartment);
                employee.setStatus(1);
                employee.setEmail(employeeRequest.getEmail());
                employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
                employee.setSalary(employeeRequest.getSalary());
                return employeeRepository.save(employee);
            }
            return null;
        }).toList();
        return new ResponseEntity<>(finalDepartment, HttpStatus.OK);
    }
}
