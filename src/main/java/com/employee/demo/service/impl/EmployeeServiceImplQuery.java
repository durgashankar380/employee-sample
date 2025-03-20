package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepositoryQuery;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeServiceQuery;

@Service
public class EmployeeServiceImplQuery implements EmployeeServiceQuery {
	private final EmployeeRepositoryQuery repository;

	public EmployeeServiceImplQuery(EmployeeRepositoryQuery repository1) {
		this.repository = repository1;
	}

	@Override
	public Map<String, Double> getTotalSalaryPerDepartment() {
		try {
		List<Object[]> result = repository.getTotalSalaryPerDepartment(); 
		Map<String, Double> salaryMap = new HashMap<>();
		for(Object[] obj : result) {
			String department = obj[0] != null ? (String) obj[0]:"Unknown";
			Double totalSalary = obj[1] != null ? (Double) obj[1]:0.0;
			salaryMap.put(department, totalSalary);
		}
		return salaryMap;
	}catch(Exception e) {
		System.out.println(e.getMessage());
		return Collections.emptyMap();
	}
	}

	@Override
	public List<String> getUniqueEmployeeDepartments() {
		return repository.getUniqueEmployeeDepartments();
	}

	@Override
	public Map<String, List<EmployeeResponse>> getEmployeeGroupByDepartment() {
		try {
		List<Object[]> result = repository.getEmployeeGroupByDepartment();
		Map<String, List<EmployeeResponse>> deptMap = new HashMap<>();
				for(Object[] obj : result) {
					Long id = (Long) obj[0];
					String name = (String) obj[1];
					String department = obj[2] != null ? (String) obj[2]:"Unknown";
					Double salary = (Double) obj[3];
			
			EmployeeResponse employee = new EmployeeResponse(id,name,department,salary);
			deptMap.computeIfAbsent(department, k-> new ArrayList<>()).add(employee);
		}
		return deptMap;
	}catch(Exception e) {
		System.out.println(e.getMessage());
	return Collections.emptyMap();
		}
	}

	@Override
	public Map<Long, EmployeeResponse> getEmployeeByIdMap() {
		List<Object[]> result = repository.getEmployeeByIdMap();
		Map<Long, EmployeeResponse> idMap = new HashMap<>();
		for(Object[] obj : result) {
			Long id = (Long) obj[0];
			String name = (String) obj[1];
			String department = (String) obj[2];
			Double salary = (Double) obj[3];
			
			EmployeeResponse employee = new EmployeeResponse(id,name,department,salary);
			idMap.put(id, employee);
		}
		return idMap;
	}

	@Override
	public List<Employee> getEmployeeSortBySalary() {
		return repository.getEmployeeSortBySalary();
	}

	@Override
	public Map<String, Long> getDepartmentEmployeeCount() {
		try {
		List<Object[]> result = repository.getDepartmentEmployeeCount();
		Map<String, Long> countMap = new HashMap<>();
		for(Object[] obj : result) {
			String department = obj[0] != null ? (String) obj[0]:"Unknown";
			Long count = obj[1] != null ? (Long) obj[1]:0L;
			countMap.put(department, count);
		}
		return countMap;
	} catch(Exception e) {
		System.out.println(e.getMessage());
		return Collections.emptyMap();
	}
	}
		
	

	@Override
	public List<String> getAllEmployeeName() {
		return repository.getAllEmployeeName();
	}

	@Override
	public List<Employee> getEmployeeFirstInFirstOut() {
		return repository.getEmployeeFirstInFirstOut();
	}

	@Override
	public List<Employee> getEmployeeLastInFirstOut() {
		return repository.getEmployeeLastInFirstOut();
	}

	@Override
	public List<Employee> getSecondHighestSalary() {
		return repository.getSecondHighestSalary();
	}

	@Override
	public List<Object[]> getEmployeeEarnAboveDepartmentAverageSalary() {
		return repository.getEmployeeEarnAboveDepartmentAverageSalary();
	}

	@Override
	public List<String> getDepartmentWithHighestTotalSalary() {
		return repository.getDepartmentWithHighestTotalSalary();
	}

	@Override
	public List<String> mostCommonFirstLetterinEmployeeNames() {
		return repository.mostCommonFirstLetterinEmployeeNames();
	}



	@Override
	public Map<String, List<EmployeeResponse>> getTop3HighestPaidEmployeePerDepartment() {
		List<Employee> result = repository.getTop3HighestPaidEmployeePerDepartment();
		Map<String, List<EmployeeResponse>> topMap = new HashMap<>();
		for(Employee emp : result) {
			String department = emp.getDepartment();
			EmployeeResponse employeeResponse = new EmployeeResponse(emp.getId(),emp.getName(),department,emp.getSalary());
			
			topMap.putIfAbsent(department, new ArrayList<>());
			if(topMap.get(department).size()<3) {
				topMap.get(department).add(employeeResponse);
			}
		}
		return topMap;
	}
}
