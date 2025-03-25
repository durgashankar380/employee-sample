package com.employee.demo.service;

import java.util.List;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.model.Employee;
import com.employee.demo.response.ResponseEmployee;

public interface EmployeeQService {
	 //1 sorted by salary
    List<Employee> getEmployeesSortedBySalary();
    
    //2 get all unique departments
   // List<String> getUniqueDepartments();
    
    //3 get all employee names
   // List<String> getEmployeeNames();
    
    //4 get all employees in FIFO order
   List<Employee> getEmployeesInFIFOOrder();
    
    //5 get all employees in LIFO order
    List<Employee> getEmployeesInLIFOOrder();
    
    //6 get employee count per department
  //  Map<String,Integer> getEmployeeCountPerDepartment();
    
    //7 get employees grouped by department
  //  Map<String, List<Employee>> getEmployeesGroupedByDepartment();
     
     //8 get total salary per department
 //   List<Object[]> getTotalSalaryPerDepartment();
    
    //9 get employees As map
    Map<Long, Employee> getEmployeesAsMap();
    
    //10
    List<ResponseEmployee> getEmployeesWithSecondHighestSalary();
    
  //11 get department with highest total salary
  //  String findDepartmentWithHighestTotalSalary();
   
    //12
 //   Map<String, List<Employee>> getEmployeesAboveDepartmentAverage();
    
    //13
  //  Map<String, List<Employee>>  getTop3EmployeesByDepartment();
    
    //14
    char getMostCommonFirstLetter();
    
}

