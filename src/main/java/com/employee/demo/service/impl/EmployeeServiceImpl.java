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
    	try {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setDepartment(employeeRequest.getDepartment());
        employee.setSalary(employeeRequest.getSalary());
        Employee savedEmployee = repository.save(employee);
        return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getDepartment(), savedEmployee.getSalary());
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    	throw new RuntimeException("Failed to add employee ",e);
    		}
    	}
    
    @Override
    public Map<String, Double> getTotalSalaryPerDepartment() {
    	try {
        List<Employee> employees = repository.findAll();
        Map<String, Double> totalSalaryMap = new HashMap<>();
        for (Employee emp : employees) {
            totalSalaryMap.put(emp.getDepartment(), totalSalaryMap.getOrDefault(emp.getDepartment(), 0.0) + emp.getSalary());
        }
        return totalSalaryMap;
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    		throw new RuntimeException("Failed to get Total Salary",e);
    	}
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
    	try {
        List<Employee> employees = repository.findAll();
        Map<String, List<EmployeeResponse>> groupedEmployees = new HashMap<>();
        for (Employee emp : employees) {
            groupedEmployees.putIfAbsent(emp.getDepartment(), new ArrayList<>());
            groupedEmployees.get(emp.getDepartment()).add(new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return groupedEmployees;
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    		throw new RuntimeException("Failed to group employee",e);
    	}
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments() {
    	try {
        List<Employee> employees = repository.findAll();
        Set<String> uniqueDepartments = new HashSet<>();
        for (Employee emp : employees) {
            uniqueDepartments.add(emp.getDepartment());
        }
        return uniqueDepartments;
    	}
    	catch(Exception e) {
    		System.err.println("Failed to fetch Unique Employee"+e.getMessage());
    		return Collections.emptySet();
    	}
    }

    @Override
    public Map<Long, EmployeeResponse> getEmployeesByIdMap() {
    	try {
        List<Employee> employees = repository.findAll();
        Map<Long, EmployeeResponse> employeeMap = new HashMap<>();
        for (Employee emp : employees) {
            employeeMap.put(emp.getId(), new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
        }
        return employeeMap;
    	}
    	catch(Exception e) {
    		System.err.println(e.getMessage());
    		return Collections.emptyMap();
    	}
    }

	@Override
	public List<Employee> findAllByOrderBySalaryDesc() {
		try {
		List<Employee> employees = repository.findAll();
		Collections.sort(employees,new Comparator<Employee>() {
			@Override
			public int compare(Employee e1,Employee e2) {
				return Double.compare(e2.getSalary(),e1.getSalary());
			}
		});
		return employees;
		}catch(Exception e) {
			System.err.println("Failed fetching employee sorted by Salary"+e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<String> getAllEmpNames() {
		try {
		List<Employee> employees = repository.findAll();
		List<String> empName = new ArrayList<>();
		
		for(Employee e : employees) {
			empName.add(e.getName());
		}
		return empName;
		}catch(Exception e) {
			System.err.println("Failed to fetch employee by names"+e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public Map<String, Integer> getDepartmentEmployeeCount() {
		try {
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
		}catch(Exception e) {
			System.err.println("Failed to fetch employee department count"+e.getMessage());
			return Collections.emptyMap();
		}
		}

	@Override
	public List<Employee> addMultipleEmployee(List<Employee> employees) {
		try {
			return repository.saveAll(employees);
		}catch(Exception e) {
			System.err.println("Error in adding multiple employee"+e.getMessage());
			return Collections.emptyList();
		}
		}
		

	@Override
	public boolean deleteEmployeeById(Long id) {
		try {
			boolean isPresent = repository.existsById(id);
			if(!isPresent) return false;
			repository.deleteById(id);
			return true;
		}catch(Exception e) {
			System.err.println("Error deleting employee by id "+e.getMessage());
			return false;
		}
		}


	@Override
	public void updateEmployeeById(Long id, Employee e) {
		try {
		Optional<Employee> op = repository.findById(id);
		if(op.isPresent()) {
			Employee emp = op.get();
			emp.setName(e.getName());
			emp.setDepartment(e.getDepartment());
			emp.setSalary(e.getSalary());
			repository.save(emp);
			}else {
				System.out.println("Employee with id "+ id +"not found");
			}
		}
		catch(Exception ex) {
			System.err.println("Error updating employee by id"+ id +": "+ex.getMessage());
		}
		
	}


	@Override
	public List<Employee> getEmployeeInFirstInFirstOut() {
		try {
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
		catch(Exception e) {
			System.err.println("Error fetching employee in FIFO"+e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<Employee> getEmployeeInLastInFirstOut() {
		try {
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
		} catch(Exception e) {
			System.err.println("Error fetching employee in LIFO"+e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public Map<String, List<Employee>> getTop3HighestPaidEmployeePerDepartment() {
		try {
		List<Employee> listEmp = repository.findAll();
		if(listEmp == null || listEmp.isEmpty()) {
			return new HashMap<>();
		}
			Map<String, List<Employee>> employeeByDepartment = new HashMap<>();
			for(int i=0;i<listEmp.size();i++) {
				Employee listEmp1 = listEmp.get(i);
				if(!employeeByDepartment.containsKey(listEmp1.getDepartment())) {
					employeeByDepartment.put(listEmp1.getDepartment(), new ArrayList<>());
				}
				employeeByDepartment.get(listEmp1.getDepartment()).add(listEmp1);
			}
			for (Map.Entry<String, List<Employee>> entry : employeeByDepartment.entrySet()) {
	            List<Employee> departmentEmployee = entry.getValue();
	            int n = departmentEmployee.size();
	            for (int j = 0; j < n - 1; j++) {
	                for (int k = 0; k < n - j - 1; k++) {
	                    if (departmentEmployee.get(k).getSalary() < departmentEmployee.get(k + 1).getSalary()) {
	                        Employee temp = departmentEmployee.get(k);
	                        departmentEmployee.set(k, departmentEmployee.get(k + 1));
	                        departmentEmployee.set(k + 1, temp);
	                    }
	                }
	            }

	            // Keep only top 3 books
	            if (departmentEmployee.size() > 3) {
	            	departmentEmployee.subList(3, departmentEmployee.size()).clear();
	            }
	        }
	        return employeeByDepartment;
		}catch(Exception e) {
			System.err.println("Failed to fetch top 3 highest paid employee"+e.getMessage());
			return Collections.emptyMap();
		}
	    }




	@Override
	public List<Employee> getSecondHighestSalary() {
		try {
		List<Employee> listEmp = repository.findAll();
		if(listEmp == null || listEmp.size()==0) {
			return new ArrayList<Employee>();
		}
		int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;
        for (int i = 0; i < listEmp.size(); i++) {
            int count = (int) listEmp.get(i).getSalary();
            if (count > highest) {
                secondHighest = highest;
                highest = count;
            } else if (count > secondHighest && count < highest) {
                secondHighest = count;
            }
        }
        
        if (secondHighest == Integer.MIN_VALUE) {
            return new ArrayList<Employee>();
        }
        
        List<Employee> secondHighestSalary = new ArrayList<Employee>();
        for (int i = 0; i < listEmp.size(); i++) {
            if (listEmp.get(i).getSalary() == secondHighest) {
            	secondHighestSalary.add(listEmp.get(i));
            }
        }
        return secondHighestSalary;
		}catch(Exception e) {
			System.err.println("Failed in second highest salary"+e.getMessage());
			return Collections.emptyList();
		}
    }




	@Override
	public String getDepartmentWithHighestTotalSalary() {
		    try {
		        List<Employee> listEmp = repository.findAll();
		        if (listEmp == null || listEmp.isEmpty()) {
		            return "No Salary Available";
		        }

		        // Map to store total salary per department
		        Map<String, Double> salarySumByDepartment = new HashMap<>();

		        for (Employee emp : listEmp) {
		            salarySumByDepartment.merge(emp.getDepartment(), emp.getSalary(), Double::sum);
		        }

		        // Find the department with the highest total salary
		        String highestDepartment = null;
		        double maxSalary = Double.MIN_VALUE;

		        for (Map.Entry<String, Double> entry : salarySumByDepartment.entrySet()) {
		            if (entry.getValue() > maxSalary) {
		                maxSalary = entry.getValue();
		                highestDepartment = entry.getKey();
		            }
		        }

		        return highestDepartment != null ? highestDepartment : "No department found";
		    } catch (Exception e) {
		        // Log error (use a logger in production)
		        System.err.println("Error fetching department with highest total salary: " + e.getMessage());
		        return "Error retrieving data";
		    }
		}



	@Override
	public List<Employee> getEmployeeEarnAboveDepartmentAverageSalary() {
		try {
		List<Employee> listEmp = repository.findAll();
		if(listEmp == null || listEmp.isEmpty()) {
			return Collections.emptyList();
		}
		Map<String, List<Double>> departmentSalary = new HashMap<>();
        for (int i = 0; i < listEmp.size(); i++) {
            Employee emp1 = listEmp.get(i);
            String department = emp1.getDepartment();
            double salary = emp1.getSalary();

            if (!departmentSalary.containsKey(department)) {
            	departmentSalary.put(department, new ArrayList<>());
            }
            departmentSalary.get(department).add(salary);
        }
        Map<String, Double> departmentSalary1 = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : departmentSalary.entrySet()) {
            String department = entry.getKey();
            List<Double> salaryList = entry.getValue();

            int sum = 0;
            for (int j = 0; j < salaryList.size(); j++) {
                sum += salaryList.get(j);
            }
            double avg = (double) sum / salaryList.size();
            departmentSalary1.put(department, avg);
        }
        List<Employee> resultEmployees = new ArrayList<>();
        for (int i = 0; i < listEmp.size(); i++) {
            Employee emp1 = listEmp.get(i);
            String department = emp1.getDepartment();
            if (emp1.getSalary() > departmentSalary1.get(department)) {
                resultEmployees.add(emp1);
            }
        }

        return resultEmployees;
		}catch(Exception e) {
			System.err.println("Error fetching employees earning above department average salary:"+e.getMessage());
			return Collections.emptyList();
		}
	}




	@Override
	public String mostCommonFirstLetterinEmployeeNames() {
		try {
		List<Employee> listEmp = repository.findAll();
        if (listEmp == null || listEmp.isEmpty()) {
            return "No names available";
        }
        Map<Character, Integer> letterCount = new HashMap<>();

        for (int i = 0; i < listEmp.size(); i++) {
            String name = listEmp.get(i).getName();
            if (name != null && !name.isEmpty()) {
                char firstLetter = Character.toUpperCase(name.charAt(0));

                if (!letterCount.containsKey(firstLetter)) {
                    letterCount.put(firstLetter, 1);
                } else {
                    letterCount.put(firstLetter, letterCount.get(firstLetter) + 1);
                }
            }
        }
        char mostCommonLetter = '\0';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : letterCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonLetter = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return maxCount > 0 ? String.valueOf(mostCommonLetter) : "No common letter found";
		}catch(Exception e) {
			System.err.println("Error finding most common first letter: "+e.getMessage());
			return "Error retrieving data";
			}
		}
	}
