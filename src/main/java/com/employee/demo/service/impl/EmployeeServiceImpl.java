package com.employee.demo.service.impl;

import java.util.*;

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
    public List<EmployeeResponse> getSortedSalaryDesc() {
        List<Employee> employees = repository.findAll();
        employees.sort((a, b) -> Double.compare(a.getSalary(), b.getSalary()));
        List<EmployeeResponse> responseList = new ArrayList<>();
        for (Employee employee : employees)
            responseList.add(new EmployeeResponse(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()));
        return responseList;
    }

    @Override
    public List<String> getEmployeesList() {
        List<Employee> employees = repository.findAll();
        List<String> names = new ArrayList<>();
        for (Employee employee : employees) names.add(employee.getName());
        return names;
    }

    @Override
    public Map<String, Long> countPerDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String, Long> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.getDepartment(), map.getOrDefault(employee.getDepartment(), (long) 0) + 1);
        }
        return map;
    }

    @Override
    public Queue<EmployeeResponse> getQueueOfEmployees() {
        List<Employee> employees = repository.findAll();
        Queue<EmployeeResponse> employeeResponseQueue = new LinkedList<>();
        for (Employee employee : employees)
            employeeResponseQueue.add(new EmployeeResponse(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()));
        return employeeResponseQueue;
    }

    @Override
    public Stack<EmployeeResponse> getStackOfEmployees() {
        List<Employee> employees = repository.findAll();
        Stack<EmployeeResponse> employeeResponseStack = new Stack<>();
        for (Employee employee : employees) {
            employeeResponseStack.push(new EmployeeResponse(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()));
        }
        return employeeResponseStack;
    }

    @Override
    public List<EmployeeResponse> addEmployeeList(List<EmployeeRequest> requestList) {
        List<EmployeeResponse> responseList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeRequest employeeRequest : requestList) {
            Employee employee = new Employee();
            employee.setName(employeeRequest.getName());
            employee.setDepartment(employeeRequest.getDepartment());
            employee.setSalary(employeeRequest.getSalary());
            employeeList.add(employee);
            //responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary() ));
            //    employeeList.add(new Employee(employeeRequest.getName(),employeeRequest.getDepartment(),employeeRequest.getSalary()));
        }
        List<Employee> employees = repository.saveAll(employeeList);
        for (Employee employee : employees)
            responseList.add(new EmployeeResponse(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()));
        return responseList;
    }

    @Override
    public List<EmployeeResponse> getTopThree() {
        List<Employee> employees = repository.findAll();
        employees.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));
        List<EmployeeResponse> responseList = new ArrayList<>();
        int count = 0;
        if (employees.size() < 3) {
            for (Employee employee : employees) {
                responseList.add(new EmployeeResponse(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()));
            }
        } else {
            for (Employee employee : employees) {
                if (count < 3) {
                    count++;
                    responseList.add(new EmployeeResponse(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()));
                } else break;
            }
        }
        return responseList;
    }

    @Override
    public String getDepartmentWithHighestSalary() {
        List<Employee> employees = repository.findAll();
        Map<String, Double> map = new HashMap<>();
        String department = "";
        double maxSalary = 0.0;
        for (Employee employee : employees) {
            String currDept = employee.getDepartment();
            Double Salary = employee.getSalary();
            map.put(currDept, map.getOrDefault(currDept, 0.0) + Salary);
            if (maxSalary < map.get(currDept)) {
                department = currDept;
                maxSalary = Salary;
            }
        }
        return department;
    }

    @Override
    public List<EmployeeResponse> getEmpEarnMoreThanAvgSalary() {
        List<EmployeeResponse> responseList = new ArrayList<>();
        List<Employee> employees = repository.findAll();
        Map<String, Double> salaryMap = new HashMap<>();
        Map<String, Integer> freqMap = new HashMap<>();
        for (Employee employee : employees) {
            double salary = employee.getSalary();
            String department = employee.getDepartment();
            salaryMap.put(department, salaryMap.getOrDefault(department, 0.0) + salary);
            freqMap.put(department, freqMap.getOrDefault(department, 0) + 1);
        }
        System.out.println(salaryMap + " before"+freqMap);
        for (String dep : salaryMap.keySet()) {
            double salary = salaryMap.get(dep);
            int freq = freqMap.get(dep);
            salaryMap.put(dep, salary / (double) freq);
        }
        System.out.println(salaryMap);
        for (Employee employee : employees) {
            String dept = employee.getDepartment();
            double salary = employee.getSalary();
            if (salaryMap.get(dept) < salary) {
                responseList.add(new EmployeeResponse(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary()));
            }
        }
        return responseList;
    }

    @Override
    public Character getMostCommonFirstLetter() {
        List<Employee> employees = repository.findAll();
        Map<Character, Integer> freq = new HashMap<>();
        char letter = '0';
        int maxfreq = 0;
        for (Employee employee : employees) {
            char currChar = employee.getName().charAt(0);
            freq.put(currChar, freq.getOrDefault(currChar, 0) + 1);
            if (maxfreq < freq.get(currChar)) {
                letter = currChar;
                maxfreq = freq.get(currChar);
            }
        }
        return letter;
    }

    @Override
    public List<EmployeeResponse> getSecondHighestSalary() {
        List<Employee> employees = repository.findAll();
        employees.sort((a, b) -> Double.compare(b.getSalary(), a.getSalary()));
        List<EmployeeResponse> responseList = new ArrayList<>();
        double maxSalary = employees.get(0).getSalary();
        double secMaxSalary = 0.0;
        for (Employee employee : employees) {
            if (employee.getSalary() < maxSalary) {
                secMaxSalary = employee.getSalary();
                break;
            }
        }
        for (Employee employee2 : employees) {
            if (employee2.getSalary() == secMaxSalary)
                responseList.add(new EmployeeResponse(employee2.getId(), employee2.getName(), employee2.getDepartment(), employee2.getSalary()));
            else if (employee2.getSalary() < secMaxSalary) {
                break;
            }
        }
        return responseList;
    }
}