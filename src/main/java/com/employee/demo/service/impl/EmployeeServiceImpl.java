package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

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




    @Override
    public List<EmployeeResponse> getEmployeesSortedBySalary() {
        List<Employee> employees = repository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();

        for (Employee emp : employees) {
            employeeResponses.add(new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }

        Collections.sort(employeeResponses, new Comparator<EmployeeResponse>() {
            @Override
            public int compare(EmployeeResponse e1, EmployeeResponse e2) {
                return Double.compare(e2.getSalary(), e1.getSalary()); // Descending order
            }
        });

        return employeeResponses;
    }




    @Override
    public List<String> getEmployeeNames() {
        List<Employee> employees = repository.findAll();
        List<String> names = new ArrayList<>();
        for (Employee emp : employees) {
            names.add(emp.getName());
        }
        return names;
    }




    @Override
    public Map<String, Long> getEmployeeCountPerDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String, Long> countMap = new HashMap<>();

        for (Employee emp : employees) {
            String department = emp.getDepartment();
            if (countMap.containsKey(department)) {
                countMap.put(department, countMap.get(department) + 1);
            } else {
                countMap.put(department, 1L);
            }
        }
        return countMap;
    }

    @Override
    public Queue<EmployeeResponse> getEmployeesAsQueue() {
        List<Employee> employees = repository.findAll();
        Queue<EmployeeResponse> queue = new LinkedList<>();

        for (Employee emp : employees) {
            queue.offer(new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return queue;
    }

    @Override
    public Stack<EmployeeResponse> getEmployeesAsStack() {
        List<Employee> employees = repository.findAll();
        Stack<EmployeeResponse> stack = new Stack<>();

        for (Employee emp : employees) {
            stack.push(new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return stack;
    }
    
    @Override
    public List<EmployeeResponse> addMultipleEmployees(List<EmployeeRequest> employeeRequests) {
        List<Employee> employees = employeeRequests.stream()
                .map(req -> new Employee(null, req.getName(), req.getDepartment(), req.getSalary()))
                .collect(Collectors.toList());

        List<Employee> savedEmployees = repository.saveAll(employees);

        return savedEmployees.stream()
                .map(emp -> new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()))
                .collect(Collectors.toList());
    }

}