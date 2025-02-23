package com.employee.demo.service.impl;

import java.util.*;

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
    public List<EmployeeResponse> getSortedSalaryDesc() {
        List<Employee> employees=repository.findAll();
        employees.sort((a,b)->Double.compare(a.getSalary(),b.getSalary()));
        List<EmployeeResponse> responseList=new ArrayList<>();
        for(Employee employee:employees)responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        return responseList;
    }

    @Override
    public List<String> getEmployeesList() {
        List<Employee> employees=repository.findAll();
        List<String> names=new ArrayList<>();
        for(Employee employee:employees)names.add(employee.getName());
        return names;
    }

    @Override
    public Map<String, Long> countPerDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String,Long> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.getDepartment(),map.getOrDefault(employee.getDepartment(),(long)0)+1);
        }
        return map;
    }

    @Override
    public Queue<EmployeeResponse> getQueueOfEmployees() {
        List<Employee> employees=repository.findAll();
        Queue<EmployeeResponse> employeeResponseQueue= new LinkedList<>();
        for(Employee employee:employees)employeeResponseQueue.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        return  employeeResponseQueue;
    }

    @Override
    public Stack<EmployeeResponse> getStackOfEmployees() {
        List<Employee> employees=repository.findAll();
        Stack<EmployeeResponse> employeeResponseStack=new Stack<>();
        for(Employee employee:employees){
            employeeResponseStack.push(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        }
        return employeeResponseStack ;
    }

    @Override
    public List<EmployeeResponse> addEmployeeList(List<EmployeeRequest> requestList) {
        List<EmployeeResponse> responseList=new ArrayList<>();
        List<Employee> employeeList=new ArrayList<>();
        for(EmployeeRequest employeeRequest:requestList){
            Employee employee=new Employee();
            employee.setName(employeeRequest.getName());
            employee.setDepartment(employeeRequest.getDepartment());
            employee.setSalary(employeeRequest.getSalary());
            employeeList.add(employee);
            //responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary() ));
            //    employeeList.add(new Employee(employeeRequest.getName(),employeeRequest.getDepartment(),employeeRequest.getSalary()));
        }
        List<Employee> employees =repository.saveAll(employeeList);
        for(Employee employee:employees)responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary() ));
        return responseList;
    }
}