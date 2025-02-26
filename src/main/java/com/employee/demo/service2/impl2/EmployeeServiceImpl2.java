package com.employee.demo.service2.impl2;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service2.EmployeeService2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl2 implements EmployeeService2 {
    private  final EmployeeRepository repository;

    public EmployeeServiceImpl2(EmployeeRepository repository) {
        this.repository = repository;
    }

//1
    @Override
    public List<Employee> getTopThree() {
//        return repository.topThreeHighestSalary();
        List<Employee> employees=repository.topThreeHighestSalary();
        System.out.println(employees);
        List<EmployeeResponse>responseList=new ArrayList<>();
        for(Employee employee:employees)
            responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        System.out.println(responseList);
        return employees;
    }
//2
    @Override
    public List<EmployeeResponse> getAll() {
        List<Employee> employees=repository.getAllEmp();
        List<EmployeeResponse> responseList=new ArrayList<>();
        for (Employee employee:employees){
            responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        }
        System.out.println(responseList);
        return responseList;
    }
//3
    @Override
    public List<EmployeeResponse> getSecondHighestSalary() {
        List<Employee> employees=repository.secondHighestSalary();
        List<EmployeeResponse>responseList=new ArrayList<>();
        for(Employee employee:employees)
            responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        return responseList;
    }
//4
    @Override
    public String getDepartmentWithHighestSalary() {
        return repository.getDepartmentWithHighestSalary();
    }
//5
    @Override
    public List<EmployeeResponse> getEmpEarnMoreThanAvgSalary() {
        return repository.getEmpEarnMoreThanAvgSalary();
    }
//6
    @Override
    public Character getMostCommonFirstLetter() {
        char c=repository.getMostCommonLetter();
        System.out.println(c);
        return c;
    }
//7 incomplete
    @Override
    public List<EmployeeResponse> addEmployeeList(List<EmployeeRequest> requestList) {
        return List.of();
    }
//8
    @Override
    public Map<String, Long> countPerDepartment() {
        List<Object[]> responseList=repository.getCountPerDepartment();
        Map<String,Long> map=new HashMap<>();
        for (Object[] objects:responseList){

            map.put((String) objects[0],(long)objects[1]);
        }
        System.out.println(map+" "+responseList);
        return map;
    }
// 9
    @Override
    public List<String> getEmployeesList() {
        List<String> strings=repository.getEmployeesList();
        System.out.println(strings);
        return strings;
    }
// 10
    @Override
    public List<EmployeeResponse> getSortedSalaryDesc() {
        List<Employee> employees=repository.getSortedSalaryDesc();
        List<EmployeeResponse> responseList=new ArrayList<>();
        for(Employee employee:employees){
            responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        }
        System.out.println(responseList);
        return responseList;
    }
// 11
    @Override
    public Set<String> getUniqueEmployeeDepartments() {
        Set<String> set=repository.getUniqueEmployeeDepartments();
        System.out.println(set);
        return set;
    }
// 12
    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        Map<String,List<EmployeeResponse>> map=new HashMap<>();
        List<Employee> employees=repository.getAllEmp();
        for(Employee employee:employees){
            String dept=employee.getDepartment();
            map.putIfAbsent(dept, new ArrayList<EmployeeResponse>());
            map.get(dept).add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        }
        return map;
    }
// 13
    @Override
    public Map<String, Double> getTotalSalaryPerDepartment() {
        List<Object[]> responseList=repository.getTotalSalaryPerDepartment();
        Map<String,Double> map=new HashMap<>();
        for (Object[] objects:responseList){
            map.put((String) objects[0],(double) objects[1]);
        }
        return map;
    }
// 14
    @Override
    public Map<Long, EmployeeResponse> getEmployeesByIdMap() {
        Map<Long,EmployeeResponse> map=new HashMap<>();
        List<Employee> employees=repository.getAllEmp();
        for(Employee employee:employees){
            long Id=employee.getId();
            map.put(Id,new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()));
        }
        return map;
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        if(employeeRequest.getName()!=null && !employeeRequest.getName().isBlank() &&
           employeeRequest.getDepartment()!=null && !employeeRequest.getDepartment().isBlank() &&
                employeeRequest.getSalary()>=Double.MIN_VALUE && employeeRequest.getSalary()<=Double.MAX_VALUE){
            System.out.println(employeeRequest);
            Employee employee=repository.addEmployee(employeeRequest.getName(),employeeRequest.getDepartment(),employeeRequest.getSalary());
            System.out.println(employee+"data added successfully ");
            return new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary());
        }
        return null;
    }

}
