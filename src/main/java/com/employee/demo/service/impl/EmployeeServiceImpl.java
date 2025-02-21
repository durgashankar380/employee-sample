package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.employeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final employeeRepository employeeRepository;

	public EmployeeServiceImpl(employeeRepository repository) {
		this.employeeRepository = repository;
	}

	@Override
	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
		Employee savedEmployee = new Employee();
		savedEmployee.setName(employeeRequest.getName());
		savedEmployee.setDepartment(employeeRequest.getDepartment());
		savedEmployee.setSalary(employeeRequest.getSalary());
		employeeRepository.save(savedEmployee);
		return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getDepartment(),
				savedEmployee.getSalary());
	}

	@Override
	public Map<String, List<Employee>> getAllEmployeeByDepartment() {
		Map<String, List<Employee>> map = new HashMap<>();
		List<Employee> allEmployee = employeeRepository.findAll();
		for (Employee employee : allEmployee) {
			String department = employee.getDepartment();
			List<Employee> departmentEmployee = new ArrayList<>();
			if (map.containsKey(department)) {
				departmentEmployee = map.get(department);
			} else {
				departmentEmployee = new ArrayList<>();
				map.put(department, departmentEmployee);
			}
			departmentEmployee.add(employee);
		}
		return map;
	}

	@Override
	public double findTotalOfSalary() {
		List<Employee> employeeList = employeeRepository.findAll();
		if (employeeList.isEmpty() || employeeList == null) {
			return 0.0;
		}
		double totalSalary = 0f;
		for (Employee employee : employeeList) {
			totalSalary = totalSalary + employee.getSalary();
		}
		return totalSalary;
	}

	@Override
	public List<String> findUniqueDepartment() {
		List<Employee> allEmp = employeeRepository.findAll();
		Set<String> allDepartment = new HashSet<>();
		for (Employee emp : allEmp) {
			allDepartment.add(emp.getDepartment());
		}
		return allDepartment.stream().toList();
	}

	@Override
	public List<Employee> findEmployeeSortedBySalary() {
		List<Employee> allEmployee = employeeRepository.findAll(Sort.by("salary").descending());
		return allEmployee;

	}

	@Override
	public List<String> findAllEmployeeName() {
		List<String> list = new ArrayList<>();
		List<Employee> allEmployee = employeeRepository.findAll();
		for (Employee employee : allEmployee) {
			list.add(employee.getName());
		}
		return list;
	}

	@Override
	public Map<String, Integer> findTotalEmployeeCountByEachDepartment() {
		Map<String, Integer> map = new HashMap<>();
		try {
			List<Employee> employeeList = employeeRepository.findAll();
			if (employeeList.isEmpty() || employeeList == null) {
				return null;
			}
			for (Employee employee : employeeList) {
				String department = employee.getDepartment();
				map.put(department, map.getOrDefault(department, 0) + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<Integer, Employee> getAllEmployeeDataOnId() {
		Map<Integer, Employee> map = new HashMap<>();
		List<Employee> allEmployee = employeeRepository.findAll();
		for (Employee employee : allEmployee) {
			int id = (int) employee.getId();
			map.put(id, employee);
		}
		return map;
	}

	@Override
	public List<Employee> findLatestWiseEmployees() {
		List<Employee> latestList = new ArrayList<>();
		List<Employee> list = employeeRepository.findAll();
		Stack<Employee> stack = new Stack<>();
		for (Employee employee : list) {
			stack.push(employee);
		}
		do {
			latestList.add(stack.peek());
			stack.pop();
		} while (!stack.isEmpty());
		return latestList;
	}

	@Override
	public List<Employee> findEarliestWiseEmployees() {
		List<Employee> earliestList = new ArrayList<>();
		List<Employee> list = employeeRepository.findAll();
		Queue<Employee> queue = new LinkedList<>();
		for (Employee employee : list) {
			queue.add(employee);
		}
		do {
			earliestList.add(queue.peek());
			queue.poll();
		} while (!queue.isEmpty());
		return earliestList;
	}

	@Override
	public List<Employee> addMultipleEmployee(List<Employee> employees) {
		List<Employee> list = employeeRepository.saveAll(employees);
		return list;
	}

}