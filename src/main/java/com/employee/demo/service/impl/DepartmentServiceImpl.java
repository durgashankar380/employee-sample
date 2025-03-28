package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.DepartmentRequestDto;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> addOrUpdateDepartment(DepartmentRequestDto departmentRequest) {
        if (departmentRequest.getName() == null) {
            return ResponseEntity.badRequest().body("Department name is required");
        }

        Department department;
        if (departmentRequest.getId() == 0) { 
            department = new Department();
            department.setName(departmentRequest.getName());
            department.setLocation(departmentRequest.getLocation());
            department.setDescription(departmentRequest.getDescription());
            department.setStatus(departmentRequest.getStatus());
        } else { 
            Optional<Department> existingDepartment = departmentRepository.findById(departmentRequest.getId());
            if (!existingDepartment.isPresent()) {
                return ResponseEntity.badRequest().body("Department does not exist");
            }
            department = existingDepartment.get();
            department.setName(departmentRequest.getName());
            department.setLocation(departmentRequest.getLocation());
            department.setDescription(departmentRequest.getDescription());
            department.setStatus(departmentRequest.getStatus());
        }

        List<Employee> employees = new ArrayList<>();  
        for (RequestEmployee reqEmp : departmentRequest.getEmployees()) {
            Employee employee;
            if (reqEmp.getId() == 0) {
                employee = new Employee();
                employee.setPassword(passwordEncoder.encode(reqEmp.getPassword())); // Encrypt password for new employee
            } else {
                Optional<Employee> existingEmployee = employeeRepository.findById(reqEmp.getId());
                if (!existingEmployee.isPresent()) {
                    return ResponseEntity.badRequest().body("Employee does not exist");
                }
                employee = existingEmployee.get();
                
                // Only update password if it's changed
                if (reqEmp.getPassword() != null && !reqEmp.getPassword().isEmpty()) {
                    employee.setPassword(passwordEncoder.encode(reqEmp.getPassword()));
                }
            }

            employee.setName(reqEmp.getName());
            employee.setSalary(reqEmp.getSalary());
            employee.setStatus(reqEmp.getStatus());
            employee.setEmail(reqEmp.getEmail());
            employee.setDepartment(department);
            employees.add(employee);
        }

        department.setEmployees(employees); 
        departmentRepository.save(department); 
        return ResponseEntity.ok("Department and employees saved successfully");
    }
}