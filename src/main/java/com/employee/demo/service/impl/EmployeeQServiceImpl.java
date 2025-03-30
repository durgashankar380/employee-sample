package com.employee.demo.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeQRepository;
import com.employee.demo.response.ResponseEmployee;
import com.employee.demo.service.EmployeeQService;

@Service
public class EmployeeQServiceImpl implements EmployeeQService {

    private final EmployeeQRepository employeeQRepository;

    public EmployeeQServiceImpl(EmployeeQRepository employeeQRepository) {
        this.employeeQRepository = employeeQRepository;
    }

    //1 sorted by salary
    @Override
    public List<Employee> getEmployeesSortedBySalary() {
    	return employeeQRepository.getEmployeesSortedBySalary();
    }
    
    //2 get all unique departments
//    @Override
//    public List<String> getUniqueDepartments() {
//        return employeeQRepository. getUniqueDepartments();
//    }
       
    //3 get all employee names
    @Override
    public List<String> getEmployeeNames() {
    	return employeeQRepository.getEmployeeNames();
    }
    
    //4 get all employees in FIFO order
    @Override
    public List<Employee> getEmployeesInFIFOOrder() {
    	return employeeQRepository.getEmployeesInFIFOOrder();
    }
    
    //5 get all employees in LIFO order
    @Override
    public List<Employee> getEmployeesInLIFOOrder() {
    	return employeeQRepository.getEmployeesInLIFOOrder();
    }
    
    //6 get employee count per department
//    @Override
//    public Map<String, Integer> getEmployeeCountPerDepartment() {
//        List<Object[]> results = employeeQRepository.getEmployeeCountPerDepartment();
//        Map<String, Integer> departmentCountMap = new HashMap<>();
//
//        for (Object[] row : results) {
//            String department = (String) row[0]; // First column: department name
//            Integer count = ((Number) row[1]).intValue(); // Second column: employee count
//            departmentCountMap.put(department, count);
//        }
//
//        return departmentCountMap;
//    }

    
    //7 get employees grouped by department
//    @Override
//    public Map<String, List<Employee>> getEmployeesGroupedByDepartment() {
//        List<Employee> employees = employeeQRepository.getEmployeesGroupedByDepartment();
//        
//        return employees.stream()
//            .collect(Collectors.groupingBy(Employee::getDepartment));
//    }
    
    //8 get total salary per department
//    @Override
//    public List<Object[]> getTotalSalaryPerDepartment() {
//    	return employeeQRepository.getTotalSalaryPerDepartment();
//    }
//    
    //9 get all employees as map
    @Override
    public Map<Long, Employee> getEmployeesAsMap() {
        List<Employee> employees = employeeQRepository.getAllEmployees();
        Map<Long, Employee> employeeMap = new HashMap<>();

        for (Employee emp : employees) {
            employeeMap.put(emp.getId(), emp);
        }

        return employeeMap;
    }

    
    //10 get employee with second highest salary
    @Override
    public List<ResponseEmployee> getEmployeesWithSecondHighestSalary() {
    	return employeeQRepository.getEmployeesWithSecondHighestSalary();
    }
    
  //11 get department with highest total salary
//    @Override
//    public String findDepartmentWithHighestTotalSalary() {
//        String highestSalaryDepartment = employeeQRepository.getDepartmentWithHighestTotalSalary();
//
//        // If no department is found, return a default message
//        if (highestSalaryDepartment == null) {
//            return "No department found";
//        }
//
//        return highestSalaryDepartment;
//    }
    
    
    //12 get employee who have salary above his department average
//    @Override
//    public Map<String, List<Employee>> getEmployeesAboveDepartmentAverage() {
//        List<Employee> employees = employeeQRepository.findEmployeesAboveDepartmentAverage(); // Fetch all employees
//        Map<String, List<Employee>> result = new HashMap<>();
//
//        for (Employee emp : employees) {
//            String dept = emp.getDepartment();
//
//            // If department is not present, initialize a new list
//            if (!result.containsKey(dept)) {
//                result.put(dept, new ArrayList<Employee>());
//            }
//
//            // Add employee to the respective department list
//            result.get(dept).add(emp);
//        }
//
//        return result;
//    }

    //13 get top 3 employees by department
//    @Override
//    public Map<String, List<Employee>> getTop3EmployeesByDepartment() {
//        List<Employee> employees = employeeQRepository.getTop3EmployeesByDepartment(); // Fetch all employees sorted by department & salary
//        Map<String, List<Employee>> result = new HashMap<>();
//
//        for (Employee emp : employees) {
//            String dept = emp.getDepartment();
//
//            // If department is not present, initialize a new list
//            if (!result.containsKey(dept)) {
//                result.put(dept, new ArrayList<Employee>());
//            }
//
//            // Get the current list of employees for the department
//            List<Employee> empList = result.get(dept);
//
//            // Add only top 3 employees
//            if (empList.size() < 3) {
//                empList.add(emp);
//            }
//        }
//
//        return result;
//    }

      
	//14 get most common first letter in employee names
    @Override
    public char getMostCommonFirstLetter() {
        List<Object[]> result = employeeQRepository.getMostCommonFirstLetter();

        if (result.isEmpty()) {
            return ' '; // Return a space if no records are found
        }

        Object[] mostCommonEntry = result.get(0); // The first entry has the most common letter
        return mostCommonEntry[0].toString().charAt(0);
    }
}
