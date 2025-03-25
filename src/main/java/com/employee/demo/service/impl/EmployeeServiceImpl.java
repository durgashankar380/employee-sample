package com.employee.demo.service.impl;

import java.time.LocalDateTime;
import java.util.*;

import com.employee.demo.apiResponse.ApiResponse;
import com.employee.demo.model.Department;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.JwtForgetRequest;
import com.employee.demo.request.JwtResetRequest;
import com.employee.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

@Autowired
private DepartmentRepository departmentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<?> addAndUpdateEmployee(EmployeeRequest employeeRequest) {
        Employee oldEmployee=repository.findByEmail(employeeRequest.getEmail()).orElse(null);
        Employee savedEmployee = null;
        if(employeeRequest.getId()==0) {
            if(oldEmployee==null) {
                Employee employee = new Employee();
                employee.setName(employeeRequest.getName());
//                employee.setDepartment(employeeRequest.getDepartment());
                employee.setSalary(employeeRequest.getSalary());
                employee.setEmail(employeeRequest.getEmail());
                employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
                employee.setStatus(1);
                savedEmployee = repository.save(employee);
                return new ResponseEntity<>(new EmployeeResponse(savedEmployee), HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_EMAIL_ALREADY_USED);
        }else{
            savedEmployee=repository.findById(employeeRequest.getId()).orElse(null);
            if(savedEmployee==null){
                return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_NOT_FOUND);
            }else{
                if(oldEmployee==null) {
                    savedEmployee.setName(employeeRequest.getName().isBlank() && employeeRequest.getName().isEmpty() ?
                            savedEmployee.getName() : employeeRequest.getName());
//                    savedEmployee.setDepartment(employeeRequest.getDepartment().isBlank() && employeeRequest.getDepartment().isEmpty() ?
//                            savedEmployee.getDepartment() : employeeRequest.getDepartment());
                    savedEmployee.setSalary(employeeRequest.getSalary() != null && employeeRequest.getSalary() < 0 ?
                            savedEmployee.getSalary() : employeeRequest.getSalary());
                    return new ResponseEntity<>(new EmployeeResponse(repository.save(savedEmployee)), HttpStatus.OK);
                }
                return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_EMAIL_ALREADY_USED);
            }
        }
    }

    @Override
    public Map<String, Double> getTotalSalaryPerDepartment() {
        List<Employee> employees = repository.findAll();
        Map<String, Double> totalSalaryMap = new HashMap<>();
        for (Employee emp : employees) {
            totalSalaryMap.put(emp.getDepartment().getName(), totalSalaryMap.getOrDefault(emp.getDepartment().getName(), 0.0) + emp.getSalary());
        }
        return totalSalaryMap;
    }

//    @Override
//    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
//        List<Employee> employees = repository.findAll();
//        Map<String, List<EmployeeResponse>> groupedEmployees = new HashMap<>();
//        for (Employee emp : employees) {
//            groupedEmployees.putIfAbsent(emp.getDepartment(), new ArrayList<>());
//            groupedEmployees.get(emp.getDepartment()).add(new EmployeeResponse(emp));
//        }
//        return groupedEmployees;
//    }

//    @Override
//    public Set<String> getUniqueEmployeeDepartments() {
//        List<Employee> employees = repository.findAll();
//        Set<String> uniqueDepartments = new HashSet<>();
//        for (Employee emp : employees) {
//            uniqueDepartments.add(emp.getDepartment());
//        }
//        return uniqueDepartments;
//    }

    @Override
    public Map<Long, EmployeeResponse> getEmployeesByIdMap() {
        List<Employee> employees = repository.findAll();
        Map<Long, EmployeeResponse> employeeMap = new HashMap<>();
        for (Employee emp : employees) {
            employeeMap.put(emp.getId(), new EmployeeResponse(emp));
        }
        return employeeMap;
    }

    @Override
    public List<EmployeeResponse> getSortedSalaryDesc() {
        List<Employee> employees = repository.findAll();
        employees.sort((a, b) -> Double.compare(a.getSalary(), b.getSalary()));
        List<EmployeeResponse> responseList = new ArrayList<>();
        for (Employee employee : employees)
            responseList.add(new EmployeeResponse(employee));
        return responseList;
    }

    @Override
    public List<String> getEmployeesList() {
        List<Employee> employees = repository.findAll();
        List<String> names = new ArrayList<>();
        for (Employee employee : employees) names.add(employee.getName());
        return names;
    }

//    @Override
//    public Map<String, Long> countPerDepartment() {
//        List<Employee> employees = repository.findAll();
//        Map<String, Long> map = new HashMap<>();
//        for (Employee employee : employees) {
//            map.put(employee.getDepartment(), map.getOrDefault(employee.getDepartment(), (long) 0) + 1);
//        }
//        return map;
//    }

    @Override
    public Queue<EmployeeResponse> getQueueOfEmployees() {
        List<Employee> employees = repository.findAll();
        Queue<EmployeeResponse> employeeResponseQueue = new LinkedList<>();
        for (Employee employee : employees)
            employeeResponseQueue.add(new EmployeeResponse(employee));
        return employeeResponseQueue;
    }

    @Override
    public Stack<EmployeeResponse> getStackOfEmployees() {
        List<Employee> employees = repository.findAll();
        Stack<EmployeeResponse> employeeResponseStack = new Stack<>();
        for (Employee employee : employees) {
            employeeResponseStack.push(new EmployeeResponse(employee));
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
//            employee.setDepartment(employeeRequest.getDepartment());
            employee.setSalary(employeeRequest.getSalary());
            employeeList.add(employee);
            //responseList.add(new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary() ));
            //    employeeList.add(new Employee(employeeRequest.getName(),employeeRequest.getDepartment(),employeeRequest.getSalary()));
        }
        List<Employee> employees = repository.saveAll(employeeList);
        for (Employee employee : employees)
            responseList.add(new EmployeeResponse(employee));
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
                responseList.add(new EmployeeResponse(employee));
            }
        } else {
            for (Employee employee : employees) {
                if (count < 3) {
                    count++;
                    responseList.add(new EmployeeResponse(employee));
                } else break;
            }
        }
        return responseList;
    }

//    @Override
//    public String getDepartmentWithHighestSalary() {
//        List<Employee> employees = repository.findAll();
//        Map<String, Double> map = new HashMap<>();
//        String department = "";
//        double maxSalary = 0.0;
//        for (Employee employee : employees) {
//            String currDept = employee.getDepartment();
//            Double Salary = employee.getSalary();
//            map.put(currDept, map.getOrDefault(currDept, 0.0) + Salary);
//            if (maxSalary < map.get(currDept)) {
//                department = currDept;
//                maxSalary = Salary;
//            }
//        }
//        return department;
//    }

//    @Override
//    public List<EmployeeResponse> getEmpEarnMoreThanAvgSalary() {
//        List<EmployeeResponse> responseList = new ArrayList<>();
//        List<Employee> employees = repository.findAll();
//        Map<String, Double> salaryMap = new HashMap<>();
//        Map<String, Integer> freqMap = new HashMap<>();
//        for (Employee employee : employees) {
//            double salary = employee.getSalary();
//            String department = employee.getDepartment();
//            salaryMap.put(department, salaryMap.getOrDefault(department, 0.0) + salary);
//            freqMap.put(department, freqMap.getOrDefault(department, 0) + 1);
//        }
//        System.out.println(salaryMap + " before"+freqMap);
//        for (String dep : salaryMap.keySet()) {
//            double salary = salaryMap.get(dep);
//            int freq = freqMap.get(dep);
//            salaryMap.put(dep, salary / (double) freq);
//        }
//        System.out.println(salaryMap);
//        for (Employee employee : employees) {
//            String dept = employee.getDepartment();
//            double salary = employee.getSalary();
//            if (salaryMap.get(dept) < salary) {
//                responseList.add(new EmployeeResponse(employee));
//            }
//        }
//        return responseList;
//    }

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
        List<Employee> result=repository.findBySalary(secMaxSalary);
        for (Employee employee : result) {
                responseList.add(new EmployeeResponse(employee));
        }
        return responseList;
    }

    @Override
    public ResponseEntity<?> registerEmployee(EmployeeRequest employeeRequest) {
        Department department=departmentRepository.findByNameAndLocation(employeeRequest.getDepartment().getName(),employeeRequest.getDepartment().getLocation()).orElse(null);
        if(department==null){
            Department newDepartment=new Department();
            newDepartment.setName(employeeRequest.getDepartment().getName());
            newDepartment.setLocation(employeeRequest.getDepartment().getLocation());
            newDepartment.setDescription(employeeRequest.getDepartment().getDescription());
            department=departmentRepository.save(newDepartment);
        }

        Employee oldEmployee=repository.findByEmail(employeeRequest.getEmail()).orElse(null);
        if(oldEmployee==null) {
            Employee employee = new Employee();
            employee.setName(employeeRequest.getName());
            employee.setDepartment(department);
            employee.setSalary(employeeRequest.getSalary());
            employee.setEmail(employeeRequest.getEmail());
            employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
            employee.setStatus(1);
            return new ResponseEntity<>(repository.save(employee), HttpStatus.CREATED);
        }return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_EMAIL_ALREADY_USED);
    }

    @Override
    public void setLastLogin(String email) {
        Employee employee=repository.findByEmail(email).orElse(null);
        if(employee!=null){
            employee.setLastLogin(LocalDateTime.now().toString());
            repository.save(employee);
        }
    }

    @Override
    public ResponseEntity<?> forgetPassword(JwtForgetRequest request) {
        if(request.getEmail()==null||request.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body(ApiResponse.PLEASE_ENTER_EMAIL);
        }else if(request.getCnfPassword()==null || request.getCnfPassword().isBlank()){
            return ResponseEntity.badRequest().body(ApiResponse.PLEASE_ENTER_CNF_PASSWORD);
        }else if(request.getNewPassword()==null || request.getNewPassword().isBlank()){
            return ResponseEntity.badRequest().body(ApiResponse.PLEASE_ENTER_NEW_PASSWORD);
        }else  if(!request.getNewPassword().equals(request.getCnfPassword())){
            return ResponseEntity.badRequest().body(ApiResponse.NEW_PASSWORD_CNF_PASSWORD_NOT_MATCH);
        }
        Employee employee=repository.findByEmail(request.getEmail()).orElse(null);

        if(employee==null){
            return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_NOT_FOUND);
        }
        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(employee);
        return ResponseEntity.ok(ApiResponse.EMPLOYEE_PASSWORD_SUCCESSFULLY_UPDATED);
    }

    @Override
    public ResponseEntity<?> resetPassword(JwtResetRequest resetRequest) {
        if(resetRequest.getEmail()==null||resetRequest.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body(ApiResponse.PLEASE_ENTER_EMAIL);
        }else if(resetRequest.getPrevPassword()==null || resetRequest.getPrevPassword().isBlank()){
            return ResponseEntity.badRequest().body(ApiResponse.PLEASE_ENTER_PREVOIUS_PASSWORD);
        }else if(resetRequest.getCnfPassword()==null || resetRequest.getCnfPassword().isBlank()){
            return ResponseEntity.badRequest().body(ApiResponse.PLEASE_ENTER_CNF_PASSWORD);
        }else if(resetRequest.getNewPassword()==null || resetRequest.getNewPassword().isBlank()){
            return ResponseEntity.badRequest().body(ApiResponse.PLEASE_ENTER_NEW_PASSWORD);
        }else  if(!resetRequest.getNewPassword().equals(resetRequest.getCnfPassword())){
            return ResponseEntity.badRequest().body(ApiResponse.NEW_PASSWORD_CNF_PASSWORD_NOT_MATCH);
        }
        Employee employee=repository.findByEmail(resetRequest.getEmail()).orElse(null);
        if(employee==null){
            return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_NOT_FOUND);
        }else if(!verifyPassword(employee.getPassword(),resetRequest.getPrevPassword())){
            return ResponseEntity.ok(ApiResponse.ENTERED_PREVIOUS_PASSWORD_WRONG);
        }
        employee.setPassword(passwordEncoder.encode(resetRequest.getNewPassword()));
        repository.save(employee);
        return ResponseEntity.ok(ApiResponse.EMPLOYEE_PASSWORD_SUCCESSFULLY_UPDATED);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    boolean verifyPassword(String realPassword,String prevPassword){
       return passwordEncoder.matches(prevPassword,realPassword);
    }

}