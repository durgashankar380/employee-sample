package com.employee.demo.service.impl;

import java.util.ArrayList;
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
	private final EmployeeRepositoryQuery repository1;

	public EmployeeServiceImplQuery(EmployeeRepositoryQuery repository1) {
		this.repository1 = repository1;
	}

	@Override
	public Map<String, Double> getTotalSalaryPerDepartment() {
		List<Object[]> result = repository1.getTotalSalaryPerDepartment(); 
		Map<String, Double> salaryMap = new HashMap<>();
		for(Object[] obj : result) {
			String department = (String) obj[0];
			Double totalSalary = (Double) obj[1];
			salaryMap.put(department, totalSalary);
		}
		return salaryMap;
	}

	@Override
	public List<String> getUniqueEmployeeDepartments() {
		return repository1.getUniqueEmployeeDepartments();
	}

	@Override
	public Map<String, List<EmployeeResponse>> getEmployeeGroupByDepartment() {
		List<Object[]> result = repository1.getEmployeeGroupByDepartment();
		Map<String, List<EmployeeResponse>> deptMap = new HashMap<>();
				for(Object[] obj : result) {
					Long id = (Long) obj[0];
					String name = (String) obj[1];
					String department = (String) obj[2];
					Double salary = (Double) obj[3];
			
			EmployeeResponse employee = new EmployeeResponse(id,name,department,salary);
			deptMap.computeIfAbsent(department, k-> new ArrayList<>()).add(employee);
		}
		return deptMap;
	}

	@Override
	public Map<Long, EmployeeResponse> getEmployeeByIdMap() {
		List<Object[]> result = repository1.getEmployeeByIdMap();
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
		return repository1.getEmployeeSortBySalary();
	}

	@Override
	public Map<String, Long> getDepartmentEmployeeCount() {
		List<Object[]> result = repository1.getDepartmentEmployeeCount();
		Map<String, Long> countMap = new HashMap<>();
		for(Object[] obj : result) {
			String department = (String) obj[0];
			Long count = (Long) obj[1];
			countMap.put(department, count);
		}
		return countMap;
	}
	

	@Override
	public List<String> getAllEmployeeName() {
		return repository1.getAllEmployeeName();
	}

	@Override
	public List<Employee> getEmployeeFirstInFirstOut() {
		return repository1.getEmployeeFirstInFirstOut();
	}

	@Override
	public List<Employee> getEmployeeLastInFirstOut() {
		return repository1.getEmployeeLastInFirstOut();
	}

	@Override
	public List<Employee> getSecondHighestSalary() {
		return repository1.getSecondHighestSalary();
	}

	@Override
	public List<Object[]> getEmployeeEarnAboveDepartmentAverageSalary() {
		return repository1.getEmployeeEarnAboveDepartmentAverageSalary();
	}

	@Override
	public List<String> getDepartmentWithHighestTotalSalary() {
		return repository1.getDepartmentWithHighestTotalSalary();
	}

	@Override
	public List<String> mostCommonFirstLetterinEmployeeNames() {
		return repository1.mostCommonFirstLetterinEmployeeNames();
	}



	@Override
	public Map<String, List<EmployeeResponse>> getTop3HighestPaidEmployeePerDepartment() {
		List<Employee> result = repository1.getTop3HighestPaidEmployeePerDepartment();
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
