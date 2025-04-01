package com.employee.demo.service.impl;

import com.employee.demo.apiStatus.APIStatus;
import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.DepartmentRequestWithList;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> getEmployeesByDepartment(long id) {
        return departmentRepository.findById(id).map(Department::getEmployee).orElse(Collections.emptyList());
    }
    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentResponse::new).toList();
    }

    @Override
    public ResponseEntity<?> createEmployee(DepartmentRequestWithList request) {
        Department savedDepartment = new Department();
        List<EmployeeRequest> invalidEmployeeRequestList = new ArrayList<>();
        if (request.getId() == 0) {
            Department department2 = departmentRepository.findByNameAndLocation(request.getName(), request.getLocation()).orElse(null);
            if (department2 == null) {
                Department department = new Department();
                department.setName(request.getName());
                department.setLocation(request.getLocation());
                department.setDescription(request.getDescription());
                department.setStatus(1);
                savedDepartment = departmentRepository.save(department);
            } else {
                savedDepartment = department2;
            }
        } else {
            Department oldDepartment = departmentRepository.findById(request.getId()).orElse(null);
            if (oldDepartment != null) {

                if (request.getName().isBlank() && request.getDescription().isBlank() && request.getLocation().isBlank()) {

                    savedDepartment = oldDepartment;

                } else {

                    oldDepartment.setName(request.getName().isBlank() ? oldDepartment.getName() : request.getName());

                    oldDepartment.setLocation(request.getLocation().isBlank() ? oldDepartment.getLocation() : request.getLocation());

                    oldDepartment.setDescription(request.getDescription().isBlank() ? oldDepartment.getDescription() : request.getDescription());

                    savedDepartment = departmentRepository.save(oldDepartment);

                }

            } else {

                return ResponseEntity.badRequest().body(APIStatus.INVALID_DEPARTMENT_ID);

            }

        }

        Department finalDepartment = savedDepartment;

        List<Employee> employeeList = request.getEmployeeRequestList().stream().map(employeeRequest -> {

            if (employeeRequest.getEmployeeId() == 0) {
                Employee employee2 = employeeRepository.findByEmail(employeeRequest.getEmail()).orElse(null);
                if (employee2 == null) {
                    Employee employee = new Employee();
                    employee.setName(employeeRequest.getName());
                    employee.setDepartment(finalDepartment);
                    employee.setStatus(1);
                    employee.setEmail(employeeRequest.getEmail());
                    employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
                    employee.setSalary(employeeRequest.getSalary());
                    return employeeRepository.save(employee);
                } else {
                    return null;
                }
            } else {
                Employee oldEmployee = employeeRepository.findById(employeeRequest.getEmployeeId()).orElse(null);
                if (oldEmployee != null) {
                    oldEmployee.setName(employeeRequest.getName().isBlank() ? oldEmployee.getName() : employeeRequest.getName());
                    oldEmployee.setDepartment(finalDepartment);
                    oldEmployee.setSalary(employeeRequest.getSalary() == null ? oldEmployee.getSalary() : employeeRequest.getSalary());
                    return employeeRepository.save(oldEmployee);
                } else {
                    invalidEmployeeRequestList.add(employeeRequest);
                }
            }
            return null;
        }).toList();
        if (!invalidEmployeeRequestList.isEmpty()) {
            Object[] array = new Object[3];
            array[0] = "the list of invalid requests ";
            array[1] = invalidEmployeeRequestList;
            array[2] = new DepartmentResponse(finalDepartment);
            return new ResponseEntity<>(array, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new DepartmentResponse(finalDepartment), HttpStatus.OK);
    }


    @Override
    public Map<String, Double> getTotalSalaryByDepartment() {
        List<Department> departments = departmentRepository.findAll();
        Map<String, Double> salaryMap = new HashMap<>();

        for (Department department : departments) {
            double totalSalary = 0.0;
            if (department.getEmployee() != null) {
                for (Employee employee : department.getEmployee()) {
                    totalSalary += employee.getSalary();
                }
            }
            salaryMap.put(department.getName(), totalSalary);
        }
        return salaryMap;
    }

    @Override
    public Map<String, List<EmployeeResponse>> getEmployeesGroupedByDepartment() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, List<EmployeeResponse>> groupedEmployees = new HashMap<>();

        for (Employee emp : employees) {
            String departmentName = emp.getDepartment() != null ? emp.getDepartment().getName() : "Unknown";

            groupedEmployees.putIfAbsent(departmentName, new ArrayList<>());
            groupedEmployees.get(departmentName).add(new EmployeeResponse(emp));
        }
        return groupedEmployees;
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
    public ResponseEntity<List<EmployeeResponse>> getAllEmpByDepartment(String name) {
        Department department = departmentRepository.findByName(name);

        if (department == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList()); // Return empty list if department not found
        }
        List<Employee> employees = employeeRepository.findByDepartment(department);
        if (employees.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<EmployeeResponse> employeeResponses = employees.stream().map(EmployeeResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(employeeResponses);
    }


    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

}

