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
import java.util.TreeMap;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.helper.ExcelHelper;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.employeeRepository = repository;
	}

// add employee	
	@Override
	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
		Employee savedEmployee = new Employee();
		savedEmployee.setName(employeeRequest.getName());
		savedEmployee.setDepartment(employeeRequest.getDepartment());
		savedEmployee.setSalary(employeeRequest.getSalary());
		savedEmployee.setStatus(1);
		employeeRepository.save(savedEmployee);
		return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getDepartment(),
				savedEmployee.getSalary());
	}

// get the list of employees in departments	
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

// get the total sum of given salary of all department.	
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

// find the list of existing department
	@Override
	public List<String> findUniqueDepartment() {
		List<Employee> allEmp = employeeRepository.findAll();
		Set<String> allDepartment = new HashSet<>();
		for (Employee emp : allEmp) {
			allDepartment.add(emp.getDepartment());
		}
		return allDepartment.stream().toList();
	}

// get the list of high paid employee
	@Override
	public List<Employee> findEmployeeSortedBySalary() {
		List<Employee> allEmployee = employeeRepository.findAll(Sort.by("salary").descending());
		return allEmployee;
	}

// get the list of name of all employee
	@Override
	public List<String> findAllEmployeeName() {
		List<String> list = new ArrayList<>();
		List<Employee> allEmployee = employeeRepository.findAll();
		for (Employee employee : allEmployee) {
			list.add(employee.getName());
		}
		return list;
	}

// get count of employees in each department
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

// get data of employee on existing id
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

// get latest employees list
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

// get earliest employees list	
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

// add multiple Employees
	@Override
	public List<Employee> addMultipleEmployee(List<Employee> employees) {
		List<Employee> list = employeeRepository.saveAll(employees);
		return list;
	}

// top three highest paid employees of all department.
	@Override
	public Map<String, List<EmployeeResponse>> findTopThreeHighestPaidEmployee() {
		Map<String, List<EmployeeResponse>> result = new HashMap<>();
		Map<String, List<Employee>> map = new HashMap<>();
		List<Employee> employees = employeeRepository.findAll();

		for (Employee employee : employees) {
			String department = employee.getDepartment();
			map.computeIfAbsent(department, k -> new ArrayList<>()).add(employee);
		}

		for (Map.Entry<String, List<Employee>> entry : map.entrySet()) {
			List<Employee> employeeList = entry.getValue();
			employeeList.sort((s1, s2) -> Double.compare(s2.getSalary(), s1.getSalary()));

			List<EmployeeResponse> topThree = new ArrayList<>();
			for (int i = 0; i < Math.min(3, employeeList.size()); i++) {
				topThree.add(new EmployeeResponse(employeeList.get(i)));
			}
			result.put(entry.getKey(), topThree);
		}
		return result;
	}

// get the second most highest paid employees list	
	@Override
	public Map<Double, List<EmployeeResponse>> findSecondHighestPaidEmployees() {
		Map<Double, List<EmployeeResponse>> result = new HashMap<>();
		List<Employee> employees = employeeRepository.findAll(Sort.by("salary").descending());

		double highestPaidSalary = employees.get(0).getSalary();
		Double secondHighestSalary = null;
		List<EmployeeResponse> list = new ArrayList<>();

		for (Employee employee : employees) {
			double employeeSalary = employee.getSalary();
			if (employeeSalary < highestPaidSalary) {
				if (secondHighestSalary == null) {
					secondHighestSalary = employeeSalary;
				}
				if (employeeSalary == secondHighestSalary) {
					list.add(new EmployeeResponse(employee));
				} else {
					break;
				}
			}
		}
		if (secondHighestSalary != null) {
			result.put(secondHighestSalary, list);
		}
		return result;
	}

// get most common first letter
	@Override
	public char findMostCommonFirstWord() {
		List<Employee> list = employeeRepository.findAll();

		Map<Character, Integer> map = new TreeMap<>();
		for (Employee employee : list) {
			char firstWord = employee.getName().charAt(0);
			map.put(firstWord, map.getOrDefault(firstWord, 0) + 1);
		}
		// to find the key of maximum value
		Character maxKey = null;
		Integer maxValue = Integer.MIN_VALUE;
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() > maxValue) {
				maxValue = entry.getValue();
				maxKey = entry.getKey();
			}
		}
		return maxKey;
	}

// get the Most paid department
	@Override
	public Map<String, Double> findHighestPaidDepartment() {
		Map<String, Double> result = new HashMap<>();
		Map<String, Double> map = new HashMap<>();
		List<Employee> list = employeeRepository.findAll();
		// get total of salary by each department
		for (Employee employee : list) {
			Double salary = employee.getSalary();
			String department = employee.getDepartment();
			map.put(department, map.getOrDefault(department, 0.0) + salary);
		}
		// Get Highest paid department
		String maxKey = null;
		Double maxValue = Double.MIN_VALUE;
		for (Map.Entry<String, Double> entry : map.entrySet()) {
			if (entry.getValue() >= maxValue) {
				maxValue = entry.getValue();
				maxKey = entry.getKey();
			}
		}
		result.put(maxKey, maxValue);
		return result;
	}

// save the excel file data into database
	@Override
	public void saveFile(MultipartFile file) {
		try {
			List<Employee> list = ExcelHelper.convertExcelToListOfEmployee(file.getInputStream());
			employeeRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}