package com.employee.demo.service.impl;

import com.employee.demo.apiStatus.APIStatus;
import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.request.JwtForgetPasswordRequest;
import com.employee.demo.request.JwtResetPasswordRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmailService;
import com.employee.demo.service.EmployeeService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public DepartmentRepository departmentRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<Employee> getEmployeesByDepartment(Long id) {
        return employeeRepository.findByDepartmentId(id);
    }

    @Override
    public ResponseEntity<?> addEmployee(EmployeeRequest employeeRequest) {
        Employee savedEmployee;

        // If ID is 0, create a new Employee
        if (employeeRequest.getEmployeeId() == 0) {
            Employee employee = new Employee();
            employee.setName(employeeRequest.getName());
            employee.setSalary(employeeRequest.getSalary());
            employee.setEmail(employeeRequest.getEmail());
            employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
            employee.setStatus(1);

            // Save new employee
            savedEmployee = employeeRepository.save(employee);
            return new ResponseEntity<>(new EmployeeResponse(savedEmployee), HttpStatus.CREATED);
        }
        else {
            // If ID is not 0, update the existing Employee
            savedEmployee = employeeRepository.findById(employeeRequest.getEmployeeId()).orElse(null);

            if (savedEmployee == null) {
                return ResponseEntity.badRequest()
                        .body(APIStatus.EMPLOYEE_INVALID_ID.getMessage() + " " + employeeRequest.getEmployeeId());
            }

            // Update fields
            savedEmployee.setName(employeeRequest.getName());

            // Update department only if a valid department name is provided
            if (employeeRequest.getName() != null && !employeeRequest.getName().isBlank()) {
                Department department = departmentRepository.findByName(employeeRequest.getName());
                if (department != null) {
                    savedEmployee.setDepartment(department);
                }
            }

            // Update salary only if it is a positive value
            if (employeeRequest.getSalary() > 0) {
                savedEmployee.setSalary(employeeRequest.getSalary());
            }

            // Save updated employee
            savedEmployee = employeeRepository.save(savedEmployee);
            return new ResponseEntity<>(new EmployeeResponse(savedEmployee), HttpStatus.OK);
        }
    }

    @Override
    public Map<String, Double> getTotalSalaryByDepartment() {

        List<Employee> employees = employeeRepository.findAll();
        Map<String, Double> map = new HashMap<>();
        for (Employee emp : employees) {
            String dep = emp.getName();
            Double sal = emp.getSalary();
            map.put(dep, map.getOrDefault(dep, 0.0) + sal);
        }
        return map;
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, List<EmployeeResponse>> groupedEmployee = new HashMap<>();
        for (Employee emp : employees) {
            groupedEmployee.putIfAbsent(emp.getName(), new ArrayList<>());
            groupedEmployee.get(emp.getDepartment()).add(new EmployeeResponse(emp));
        }
        return groupedEmployee;
    }

    @Override
    public Set<String> getUniqueEmployeeDepartments() {
        List<Employee> employees = employeeRepository.findAll();
        Set<String> uniqueDepartments = new HashSet<>();
        for (Employee emp : employees) {
            uniqueDepartments.add(emp.getName());
        }
        return uniqueDepartments;
    }

    @Override
    public Map<Long, EmployeeResponse> getEmployeeById() {
        List<Employee> employee = employeeRepository.findAll();
        Map<Long, EmployeeResponse> employeeMap = new HashMap<>();
        for (Employee emp : employee) {
            employeeMap.put(emp.getEmployeeId(), new EmployeeResponse(emp));
        }
        return employeeMap;

    }
    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for (Employee emp : employees) {
            employeeResponses.add(new EmployeeResponse(emp));
        }
        return employeeResponses;
    }


    @Override
    public List<EmployeeResponse> getEmployeesSortedBySalary() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponse> responseList = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeResponse employeeResponse = new EmployeeResponse(employee);
            responseList.add(employeeResponse);
        }

        Collections.sort(responseList, new Comparator<EmployeeResponse>() {
            @Override
            public int compare(EmployeeResponse e1, EmployeeResponse e2) {
                return Double.compare(e2.getSalary(), e1.getSalary());
            }
        });
        return responseList;
    }

    @Override
    public Map<String, Long> getcountperDepartment() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, Long> countMap = new HashMap<>();
        for (Employee emp : employees) {
            String department = emp.getName();
            if (countMap.containsKey(department)) {
                countMap.put(department, countMap.get(department) + 1);
            } else {
                countMap.put(department, 1L);
            }
        }
        return countMap;
    }

    @Override
    public Queue<EmployeeResponse> queueOfEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        Queue<EmployeeResponse> queue = new LinkedList<>();
        for (Employee emp : employees) {
            queue.offer(new EmployeeResponse(emp));
        }
        return queue;
    }
//
    @Override
    public List<String> getAllEmployeeName() {
        List<Employee> employeeEntities = employeeRepository.findAll();
        List<String> empName = new ArrayList<>();
        for (Employee employee : employeeEntities) {
            empName.add(employee.getName());
        }
        return empName;
    }

    @Override
    public List<EmployeeResponse> addMultipleEmployees(List<EmployeeRequest> employeeRequests) {
        List<Employee> employees = employeeRequests.stream().map(req -> {
            Employee employee = new Employee();
            employee.setName(req.getName());
            employee.setSalary(req.getSalary());
          //  employee.setDepartment(req.getDepartment());
            employee.setStatus(req.getStatus());
            employee.setEmail(req.getEmail());
//            employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));

            employee.setPassword(passwordEncoder.encode(req.getPassword()));
            return employee;
        }).collect(Collectors.toList());

        List<Employee> savedEmployees = employeeRepository.saveAll(employees);

        return savedEmployees.stream().map(EmployeeResponse::new).collect(Collectors.toList());
    }

    @Override
    public Employee updateEmpById(long id, Employee employeeEntity) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(employeeEntity.getName() != null && !employeeEntity.getName().equals("") ? employeeEntity.getName() : employee.getName());
           // employee.setDepartment(employeeEntity.getDepartment() != null && !employeeEntity.getDepartment().equals("") ? employeeEntity.getDepartment() : employee.getDepartment());
            employee.setSalary(employeeEntity.getSalary());
            employee.setStatus(employeeEntity.getStatus() != null && employeeEntity.getStatus() != 0 ? employeeEntity.getStatus() : employee.getStatus());
        }
        assert employee != null;
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }


    @Override
    public ResponseEntity<?> totalSalaryByDepartment(String dept) {
        List<Employee> employeeEntityList = employeeRepository.findAll();
        Map<String, Double> map = new HashMap<>();

        for (Employee entity : employeeEntityList) {
            String department = entity.getName();
            Double salary = entity.getSalary();
            map.put(department, map.getOrDefault(department, 0.0) + salary);
        }
        if (map.containsKey(dept)) return new ResponseEntity<>(map.get(dept), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Stack<EmployeeResponse> stackOfEmployee() {
        List<Employee> employeeEntities = employeeRepository.findAll();
        Stack<EmployeeResponse> stack = new Stack<>();
        for (Employee emp : employeeEntities) {
            stack.push(new EmployeeResponse(emp));
        }
        return stack;
    }

    @Override
    public List<EmployeeResponse> thirdhighestPaidEmployee() {
        List<Employee> employees = employeeRepository.findAll();

        Map<String, List<Employee>> map = new HashMap<>();
        for (Employee emp : employees) {
            map.computeIfAbsent(emp.getName(), k -> new ArrayList<>()).add(emp);
        }
        List<EmployeeResponse> result = new ArrayList<>();
        for (List<Employee> employees1 : map.values()) {
            employees1.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
            int count = Math.min(3, employees1.size());
            for (int i = 0; i < count; i++) {
                Employee emp = employees.get(i);
                EmployeeResponse response = new EmployeeResponse(emp);
                result.add(response);
            }
        }
        return result;
    }

    @Override
    public List<EmployeeResponse> getEmployeesWithSecondHighestSalary() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.size() < 2) {
            return new ArrayList<>();
        }
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        double secondHighestSalary = employees.get(1).getSalary();
        List<EmployeeResponse> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getSalary() == secondHighestSalary) {
                result.add(new EmployeeResponse(emp));
            }
        }
        return result;
    }

    @Override
    public List<Employee> getAverageSalary() {

        List<Employee> employees = employeeRepository.findAll();
        Map<String, List<Employee>> departmentEmployees = new HashMap<>();
        Map<String, Double> departmentAverages = new HashMap<>();

        for (Employee emp : employees) {
            departmentEmployees.computeIfAbsent(emp.getName(), k -> new ArrayList<>()).add(emp);
        }

        for (Map.Entry<String, List<Employee>> entry : departmentEmployees.entrySet()) {
            double totalSalary = 0;
            for (Employee emp : entry.getValue()) {
                totalSalary += emp.getSalary();
            }
            departmentAverages.put(entry.getKey(), totalSalary / entry.getValue().size());
        }

        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getSalary() > departmentAverages.get(emp.getName())) {
                result.add(emp);
            }
        }
        return result;
    }

    @Override
    public String getDepartmentWithHighestTotalSalary() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, Double> map = new HashMap<>();
        double maxSalary = 0;
        String highestDepartment = null;
        for (Employee emp : employees) {
            String dep = emp.getName();
            Double sal = emp.getSalary();
            map.put(dep, map.getOrDefault(dep, 0.0) + sal);
            if (map.get(dep) > maxSalary) {
                maxSalary = map.get(dep);
                highestDepartment = dep;
            }
        }
        return highestDepartment;
    }

    @Override
    public Character getMostCommonFirstLetter() {
        Map<Character, Integer> map = new HashMap<>();
        List<Employee> employees = employeeRepository.findAll();
        int freq = 0;
        Character letter = '0';
        for (Employee emp : employees) {
            char c = emp.getName().charAt(0);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) >= freq) {
                freq = map.get(c);
                letter = c;
            }
        }
        return letter;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) employeeRepository.findByEmail(username).orElseThrow(()->new RuntimeException(String.valueOf(APIStatus.EMPLOYEE_NOT_FOUND)));
    }

    @Override
    public void getlastLogin(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if(employee != null){
            employee.setLastLogin(LocalDateTime.now().toString());
            employeeRepository.save(employee);
//            employee.setLastLogin(String.valueOf(LocalDateTime.now()));
        }
    }

    @Override
    public ResponseEntity<?> forgetPassword(JwtForgetPasswordRequest request) {
            Employee employee = employeeRepository.findByEmail(request.getEmail()).orElse(null);

            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }

            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                return ResponseEntity.badRequest().body("New Password and Confirm Password do not match.");
            }

            employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
            employeeRepository.save(employee);

            return ResponseEntity.ok(APIStatus.EMPLOYEE_PASSWORD_SET);
        }

    @Override
    public ResponseEntity<String> changePassword(JwtResetPasswordRequest request) {
        Employee employee = employeeRepository.findByEmail(request.getEmail()).orElse(null);
         if(employee == null){
             return ResponseEntity.ok(APIStatus.EMAIL_NOT_FOUND.getMessage());
         }
         if(!passwordEncoder.matches(request.getPreviousPassword(), employee.getPassword())){
             return ResponseEntity.ok(APIStatus.EMPLOYEE_INCORRECT_PREVIOUS_PASSWORD.getMessage());
         }
         if(!request.getNewPassword().equals(request.getConfirmPassword())){
             return ResponseEntity.badRequest().body(APIStatus.EMPLOYEE_INCORRECT_NEW_CONFORM_PASSWORD.getMessage());
         }
        employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
       employeeRepository.save(employee);
        try {
            emailService.sendPasswordResetEmail(employee.getEmail(), employee.getName());
        } catch (MessagingException e) {
            return ResponseEntity.internalServerError().body(APIStatus.EMAIL_NOT_SEND + e.getMessage());
        }
        return ResponseEntity.ok(APIStatus.EMPLOYEE_PASSWORD_RESET.getMessage());
    }




}
