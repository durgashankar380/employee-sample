package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

   
	

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());
        Employee savedEmployee = repository.save(employee);
        return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getDepartment(), savedEmployee.getSalary());
    }
    
    @Override
    public Map<String, Double> getTotalSalaryPerDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String, Double> totalSalaryMap = new HashMap<>();
        for (Employee emp : employees) {
            totalSalaryMap.put(emp.getDepartment(), totalSalaryMap.getOrDefault(emp.getDepartment(), 0.0) + emp.getSalary());
        }
        return totalSalaryMap;
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String, List<EmployeeResponse>> groupedEmployees = new HashMap<>();
        for (Employee emp : employees) {
            groupedEmployees.putIfAbsent(emp.getDepartment(), new ArrayList<>());
            groupedEmployees.get(emp.getDepartment()).add(new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return groupedEmployees;
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments() {
        List<Employee> employees = repository.findAll();
        Set<String> uniqueDepartments = new HashSet<>();
        for (Employee emp : employees) {
            uniqueDepartments.add(emp.getDepartment());
        }
        return uniqueDepartments;
    }

    @Override
    public Map<Long, EmployeeResponse> getEmployeesByIdMap() {
        List<Employee> employees = repository.findAll();
        Map<Long, EmployeeResponse> employeeMap = new HashMap<>();
        for (Employee emp : employees) {
            employeeMap.put(emp.getId(), new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return employeeMap;
    }
}