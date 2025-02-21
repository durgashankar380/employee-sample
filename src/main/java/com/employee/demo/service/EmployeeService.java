package com.employee.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;

public interface EmployeeService {

	//1
    Employee saveEmployee(RequestEmployee requestEmployee);
 
    //2
    Map<String, Double> getTotalSalaryPerDepartment();
    
    //3	
    Map<String, List<Employee>> getEmployeesGroupedByDepartment();
    
    //4
    Set<String> getUniqueDepartments();
    
    //5
    Map<Long, Employee> getEmployeesAsMap();
    
    //6
    List<Employee> saveEmployees(List<RequestEmployee> requestEmployees);
    
    
    //7
    List<Employee> getEmployeesSortedBySalaryDesc();
    
    //8
    List<String> getEmployeeNames();
    
    //9
    Map<String, Integer> getStudentCountByDepartment();
    
    //10
    List<Employee> getEmployeesInFIFOOrder();
    
    //11
    List<Employee> getEmployeesInLIFOOrder();

    //12
    ResponseEmployee getEmployeeById(Long id);
    
    //13
    Employee updateEmployee(Long id, RequestEmployee requestEmployee);
    
    //14
    void deleteEmployee(Long id);  

}
