package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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
	public List<Employee> findAllByOrderBySalaryDesc() {
		List<Employee> employees = repository.findAll();
		Collections.sort(employees,new Comparator<Employee>() {
			@Override
			public int compare(Employee e1,Employee e2) {
				return Double.compare(e2.getSalary(),e1.getSalary());
			}
		});
		return employees;
	}




	@Override
	public List<String> getAllEmpNames() {
		List<Employee> employees = repository.findAll();
		List<String> empName = new ArrayList<>();
		
		for(Employee e : employees) {
			empName.add(e.getName());
		}
		return empName;
	}




	@Override
	public Map<String, Integer> getDepartmentEmployeeCount() {
		List<Employee> employees = repository.findAll();
		Map<String,Integer> deptEmpCount = new HashMap<>();
		for(Employee e : employees) {
			String department = e.getDepartment();
			if(deptEmpCount.containsKey(department)) {
				deptEmpCount.put(department, deptEmpCount.get(department)+1);
			}
			else {
				deptEmpCount.put(department, 1);
			}
		}
		return deptEmpCount;
	}

	@Override
	public List<Employee> addMultipleEmployee(List<Employee> employees) {
			return repository.saveAll(employees);
		}

	@Override
	public boolean deleteEmployeeById(Long id) {
			boolean isPresent = repository.existsById(id);
			if(!isPresent) return false;
			repository.deleteById(id);
			return true;
		}


	@Override
	public void updateEmployeeById(Long id, Employee e) {
		Optional<Employee> op = repository.findById(id);
		if(op.isPresent()) {
			Employee emp = op.get();
			emp.setName(e.getName());
			emp.setDepartment(e.getDepartment());
			emp.setSalary(e.getSalary());
			repository.save(emp);
		}
		
	}


	@Override
	public List<Employee> getEmployeeInFirstInFirstOut() {
		List<Employee> list = new ArrayList<>();
		List<Employee> employees = repository.findAll();
		Queue<Employee> empQueue = new LinkedList<>();
		
		for(Employee e : employees) {
			empQueue.add(e);
		}
		do{	
			list.add(empQueue.peek());
			empQueue.poll();
		} while(!empQueue.isEmpty());
		return list;
	}

	@Override
	public List<Employee> getEmployeeInLastInFirstOut() {
		List<Employee> list1 = new ArrayList<>();
		List<Employee> employees = repository.findAll();
		Stack<Employee> empStack = new Stack<>();
		
		for(Employee e : employees) {
			empStack.push(e);
		}
		do{	
			list1.add(empStack.peek());
			empStack.pop();
		} while(!empStack.isEmpty());
		return list1;
	}
	
	}
	
