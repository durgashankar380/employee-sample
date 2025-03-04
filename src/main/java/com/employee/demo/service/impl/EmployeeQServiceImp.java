package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeQRepository;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeQService;

@Service
public class EmployeeQServiceImp implements EmployeeQService {
	private EmployeeQRepository employeeQRepository;

	public EmployeeQServiceImp(EmployeeQRepository repository) {
		this.employeeQRepository = repository;
	}

// find total of salary of department	
	@Override
	public Double findTotalOfSalary() {
		return employeeQRepository.findTotalSalary();
	}

// find the list of existing department
	@Override
	public List<String> findUniqueDepartment() {
		List<String> uniqueDepartment = employeeQRepository.findUniqueDepartment();
		return uniqueDepartment;
	}

// find employee list in the descending order of their salary
	@Override
	public List<Employee> findEmployeeSortedBySalary() {
		List<Employee> employee = employeeQRepository.findSortedBySalary();
		return employee;
	}

// get the list of name of all employee
	@Override
	public List<String> findAllEmployeeName() {
		List<String> list = employeeQRepository.findAllEmployeeName();
		return list;
	}

// get Latest employees list		
	@Override
	public List<Employee> findLatestWiseEmployees() {
		List<Employee> list = employeeQRepository.findAllByLatestOrder();
		return list;
	}

// get earliest employees list	
	@Override
	public List<Employee> findEarliestWiseEmployees() {
		List<Employee> list = employeeQRepository.findAllByEarlestOrder();
		return list;
	}

// get the second most highest paid employees list	
	@Override
	public Map<Double, List<Employee>> findSecondHighestPaidEmployee() {
		Map<Double, List<Employee>> result = new HashMap<>();
		List<Employee> employees = employeeQRepository.findSecondHighestSalary();
		List<Employee> list = new ArrayList<>();
		Double salary = null;
		for (Employee emp : employees) {
			salary = emp.getSalary();
			list.add(emp);
		}
		result.put(salary, list);
		return result;
	}

// get data of employee on existing id
	@Override
	public Map<Integer, Employee> getAllEmployeeDataOnId() {
		Map<Integer, Employee> map = new HashMap<>();
		List<Employee> allEmployee = employeeQRepository.findAllEmployee();
		for (Employee employee : allEmployee) {
			int id = (int) employee.getId();
			map.put(id, employee);
		}
		return map;
	}

// get highest paid department
	@Override
	public Map<String, Double> findHighestPaidDepartment() {
		Map<String, Double> countMap = new HashMap<>();
		List<Object[]> result = employeeQRepository.findHighestPaidDepartment();
		for (Object[] obj : result) {
			String department = (String) obj[0];
			Double salary = (Double) obj[1];
			countMap.put(department, salary);
		}

		return countMap;
	}

// get count of employee by department
	@Override
	public Map<String, Long> findCountOfEmployeeInDepartment() {
		List<Object[]> result = employeeQRepository.getEmployeeCountInDepartment();
		Map<String, Long> countMap = new HashMap<>();
		for (Object[] obj : result) {
			String department = (String) obj[0];
			Long count = (Long) obj[1];
			countMap.put(department, count);
		}
		return countMap;
	}

// get employee list by department
	@Override
	public Map<String, List<EmployeeResponse>> getAllEmployeeByDepartment() {
		Map<String, List<EmployeeResponse>> result = new HashMap<>();
		List<Object[]> employees = employeeQRepository.findEmployeeByDepartment();
		for (Object[] obj : employees) {
			Long id = (Long) obj[0];
			String name = (String) obj[1];
			String department = (String) obj[2];
			Double salary = (Double) obj[3];
			EmployeeResponse employee = new EmployeeResponse(id, name, department, salary);
			result.computeIfAbsent(department, k -> new ArrayList<>()).add(employee);
		}
		return result;
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

}
