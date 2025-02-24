package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.employee.demo.Exception.EmployeeNotFoundException;
import com.employee.demo.Exception.EmployeeSaveException;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.response.ResponseEmployee;
import com.employee.demo.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	// 1. add a employee
	@Override

	public Employee saveEmployee(RequestEmployee requestEmployee) {
		try {

			if (requestEmployee.getName() == null || requestEmployee.getName().isEmpty()) {
				throw new IllegalArgumentException("Name cannot be null or empty");
			}
			if (requestEmployee.getDepartment() == null || requestEmployee.getDepartment().isEmpty()) {
				throw new IllegalArgumentException("Department cannot be null or empty");
			}
			if (requestEmployee.getSalary() == null) {
				throw new IllegalArgumentException("Salary cannot be null");
			}

			Employee employee = new Employee();
			employee.setName(requestEmployee.getName());
			employee.setDepartment(requestEmployee.getDepartment());
			employee.setSalary(requestEmployee.getSalary());
			return employeeRepository.save(employee);
		}

		catch (IllegalArgumentException e) {
			throw new EmployeeSaveException("Validation Error: " + e.getMessage());
		} catch (Exception e) {
			throw new EmployeeSaveException("Failed to save employee due to an unexpected error");
		}

	}

	// 2 get the total salary per department
	@Override
	public Map<String, Double> getTotalSalaryPerDepartment() {
		try {
			List<Employee> employees = employeeRepository.findAll();
			Map<String, Double> departmentSalaryMap = new HashMap<>();

			for (Employee emp : employees) {
				String department = emp.getDepartment();
				Double salary = emp.getSalary();

				departmentSalaryMap.put(department, departmentSalaryMap.getOrDefault(department, 0.0) + salary);
			}

			return departmentSalaryMap;

		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}
	

	// 3 get the employee according their group of department
	@Override
	public Map<String, List<Employee>> getEmployeesGroupedByDepartment() {
		List<Employee> employees = employeeRepository.findAll();
		try {
		Map<String, List<Employee>> groupedEmployees = new HashMap<>();

		for (Employee employee : employees) {
			String department = employee.getDepartment();

			List<Employee> departmentEmployees = new ArrayList<>();

			if (groupedEmployees.containsKey(department)) {
				departmentEmployees = groupedEmployees.get(department);
			} else {
				departmentEmployees = new ArrayList<>();
				groupedEmployees.put(department, departmentEmployees);
			}

			departmentEmployees.add(employee);
		}

		return groupedEmployees;
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
		
	}

	// 4 get unique department (get all types of department)
	@Override
	public Set<String> getUniqueDepartments() {
		try {
		List<Employee> employees = employeeRepository.findAll();

		Set<String> uniqueDepartments = new HashSet<>();
		for (Employee employee : employees) {
			uniqueDepartments.add(employee.getDepartment());
		}
		return uniqueDepartments;
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}

	// 5 get the employee by id using map(id as key and employee details or employee
	// object as value)
	@Override
	public Map<Long, Employee> getEmployeesAsMap() {
		try {
		List<Employee> employees = employeeRepository.findAll();
		Map<Long, Employee> employeeMap = new HashMap<>();

		for (Employee employee : employees) {
			employeeMap.put(employee.getId(), employee);
		}

		return employeeMap;
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}

	// 6 add the multiple employees at once post method
	@Override
	public List<Employee> saveEmployees(List<RequestEmployee> requestEmployees) {
		try {
			
			for (RequestEmployee req : requestEmployees) {
				if (req.getName() == null || req.getName().isEmpty()) {
					throw new IllegalArgumentException("Name cannot be null or empty");
				}
				if (req.getDepartment() == null || req.getDepartment().isEmpty()) {
					throw new IllegalArgumentException("Department cannot be null or empty");
				}
				if (req.getSalary() == null) {
					throw new IllegalArgumentException("Salary cannot be null");
				}
			}

			List<Employee> employees = new ArrayList<>();
			for (RequestEmployee req : requestEmployees) {
				Employee employee = new Employee(req.getName(), req.getDepartment(), req.getSalary());
				employees.add(employee);
			}

			return employeeRepository.saveAll(employees);
		} catch (IllegalArgumentException e) {
			throw new EmployeeSaveException("Validation Error: " + e.getMessage());
		} catch (Exception e) {
			throw new EmployeeSaveException("Failed to save employees due to an unexpected error");
		}
	}

	// 7 get the employee by descending order of salary
	@Override
	public List<Employee> getEmployeesSortedBySalaryDesc() {
		try {
		List<Employee> employees = employeeRepository.findAll();

		// This is a method reference that refers to the getSalary() method of the
		// Employee class.
		// It extracts the salary of an employee.
		// comparing is static method of Comparator
		employees.sort(Comparator.comparing(Employee::getSalary));

		Collections.reverse(employees);

		return employees;
		 } catch (Exception e) {
		        throw new RuntimeException("Failed to retrieve unique departments", e);
		    }
	}

	// 8 get only names of the employees
	@Override
	public List<String> getEmployeeNames() {
		try {
		List<Employee> employees = employeeRepository.findAll();
		List<String> employeeNames = new ArrayList<>();

		for (Employee employee : employees) {
			employeeNames.add(employee.getName());
		}

		return employeeNames;
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}

	// 9 get the no of employee present per department
	@Override
	public Map<String, Integer> getStudentCountByDepartment() {
		try {
		List<Employee> employee = employeeRepository.findAll();
		Map<String, Integer> departmentCountMap = new HashMap<>();

		for (Employee emp : employee) {
			String department = emp.getDepartment();

			// If department exists, increase the count, otherwise initialize it
			departmentCountMap.put(department, departmentCountMap.getOrDefault(department, 0) + 1);
		}

		return departmentCountMap;
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}

	//10 get employees in FIFO order
	@Override
	public List<Employee> getEmployeesInFIFOOrder() {
		try {
		List<Employee> employees = employeeRepository.findAll();
		Queue<Employee> employeeQueue = new PriorityQueue<>(Comparator.comparing(Employee::getId));
		employeeQueue.addAll(employees);

		List<Employee> fifoEmp = new ArrayList<>();
		while (!employeeQueue.isEmpty()) {
			fifoEmp.add(employeeQueue.poll());
		}

		return fifoEmp;
	}catch (Exception e) {
        throw new RuntimeException("Employee Data Not Found", e);
    }
	}
	
     //11 get employees in LIFO order
	@Override
	public List<Employee> getEmployeesInLIFOOrder() {
		try {
	    List<Employee> employees = employeeRepository.findAll();
	    Stack<Employee> stack = new Stack<>();

	    for (Employee emp : employees) {
	        stack.push(emp);
	    }

	    List<Employee> lifoEmployees = new ArrayList<>();
	    while (!stack.isEmpty()) {
	        lifoEmployees.add(stack.pop());
	    }

	    return lifoEmployees;
	 } catch (Exception e) {
	        throw new RuntimeException("Employee Data Not Found", e);
	    }
	}
	
    //12 get top 3 employees by department
    @Override
    public Map<String, List<ResponseEmployee>> getTop3EmployeesByDepartment() {
        try {
            List<Employee> Employees = employeeRepository.findAll();
            Map<String, List<ResponseEmployee>> result = new HashMap<>();
            Map<String, List<Employee>> departmentMap = new HashMap<>();

            for (Employee Employee : Employees) {
                departmentMap.computeIfAbsent(Employee.getDepartment(), k -> new ArrayList<>()).add(Employee);
            }

            for (Map.Entry<String, List<Employee>> entry : departmentMap.entrySet()) {
                List<Employee> EmployeeList = entry.getValue();
                EmployeeList.sort((s1, s2) -> Double.compare(s2.getSalary(), s1.getSalary()));

                List<ResponseEmployee> top3 = new ArrayList<>();
                for (int i = 0; i < Math.min(3, EmployeeList.size()); i++) {
                    top3.add(new ResponseEmployee(EmployeeList.get(i)));
                }
                result.put(entry.getKey(), top3);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace(); 
            return Collections.emptyMap();
        }
    }

    //13 get Employee who have second highest grade
    @Override
    public List<ResponseEmployee> getEmployeesWithSecondHighestGrade() {
        try {
            List<Employee> Employees = employeeRepository.findAll();
            Set<Double> uniqueGrades = new TreeSet<>(Collections.reverseOrder());

            for (Employee Employee : Employees) {
                uniqueGrades.add(Employee.getSalary());
            }

            if (uniqueGrades.size() < 2) return new ArrayList<>();

            Iterator<Double> iterator = uniqueGrades.iterator();
            iterator.next();
            double secondHighestGrade = iterator.next();

            List<ResponseEmployee> result = new ArrayList<>();
            for (Employee Employee : Employees) {
                if (Employee.getSalary() == secondHighestGrade) {
                    result.add(new ResponseEmployee(Employee));
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //14 get department with highest total grade
    @Override
    public String getDepartmentWithHighestTotalGrade() {
        try {
            List<Employee> Employees = employeeRepository.findAll();
            Map<String, Double> departmentGradeSum = new HashMap<>();

            for (Employee Employee : Employees) {
                departmentGradeSum.put(Employee.getDepartment(),
                        departmentGradeSum.getOrDefault(Employee.getDepartment(), 0.0) + Employee.getSalary());
            }

            return departmentGradeSum.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //15 get Employees above department average
    @Override
    public List<ResponseEmployee> getEmployeesAboveDepartmentAverage() {
        try {
            List<Employee> Employees = employeeRepository.findAll();
            Map<String, Double> departmentAverage = new HashMap<>();
            Map<String, Integer> departmentCount = new HashMap<>();

            for (Employee Employee : Employees) {
                departmentAverage.put(Employee.getDepartment(),
                        departmentAverage.getOrDefault(Employee.getDepartment(), 0.0) + Employee.getSalary());
                departmentCount.put(Employee.getDepartment(),
                        departmentCount.getOrDefault(Employee.getDepartment(), 0) + 1);
            }

            departmentAverage.replaceAll((dept, sum) -> sum / departmentCount.get(dept));

            List<ResponseEmployee> result = new ArrayList<>();
            for (Employee Employee : Employees) {
                if (Employee.getSalary() > departmentAverage.get(Employee.getDepartment())) {
                    result.add(new ResponseEmployee(Employee));
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    
    //16 get most comman first letter
    @Override
    public char getMostCommonFirstLetter() {
        try {
            List<Employee> Employees =employeeRepository.findAll();
            Map<Character, Integer> letterCount = new HashMap<>();

            for (Employee Employee : Employees) {
                char firstLetter = Employee.getName().charAt(0);
                letterCount.put(firstLetter, letterCount.getOrDefault(firstLetter, 0) + 1);
            }

            return letterCount.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(' ');
        } catch (Exception e) {
            e.printStackTrace();
            return ' ';
        }
    }

    //17 
	@Override
	public ResponseEmployee getEmployeeById(Long id) {
		try {
		Employee employee = employeeRepository.findById(id).orElseThrow();
		return new ResponseEmployee(employee.getId(), employee.getName(), employee.getDepartment(),
				employee.getSalary());
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}

	//18
	@Override
	@Transactional
	public Employee updateEmployee(Long id, RequestEmployee requestEmployee) {
		try {
		Employee employee = employeeRepository.findById(id).orElseThrow();
		employee.setName(requestEmployee.getName());
		employee.setDepartment(requestEmployee.getDepartment());
		employee.setSalary(requestEmployee.getSalary());
		return employeeRepository.save(employee);
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}

	//19
	@Override
	public void deleteEmployee(Long id) {
		try {
		if (!employeeRepository.existsById(id)) {
			throw new EmployeeNotFoundException("Employee not found with ID: " + id);
		}
		employeeRepository.deleteById(id);
		 } catch (Exception e) {
		        throw new RuntimeException("Employee Data Not Found", e);
		    }
	}
	

}