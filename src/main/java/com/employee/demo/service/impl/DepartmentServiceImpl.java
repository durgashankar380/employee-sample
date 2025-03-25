package com.employee.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.request.DepartmentRequestDto;
import com.employee.demo.request.EmployeeRequestDto;
import com.employee.demo.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService{
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	    
    @Autowired
	private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

//    @Override
//    public Department saveDepartmentWithEmployees(DepartmentRequestDto departmentRequestDto) {
//        for (EmployeeRequestDto emp : departmentRequestDto.getEmployees()) {
//        	EmployeeRequestDto.setDepartment(departmentRequestDto);
//        }
//        return departmentRepository.save(departmentRequestDto);
//    }
//    
    
    @Override
    public Department saveDepartmentWithEmployees(DepartmentRequestDto departmentRequestDto) {

        Department department = new Department();
        department.setName(departmentRequestDto.getName());
        department.setLocation(departmentRequestDto.getLocation());
        department.setDescription(departmentRequestDto.getDescription());

        // Convert Employee DTOs to Employee entities
        List<Employee> employees = departmentRequestDto.getEmployees().stream().map(empDto -> {
            Employee employee = new Employee();
            employee.setName(empDto.getName());
            employee.setSalary(empDto.getSalary());
            employee.setEmail(empDto.getEmail());
            
            String encryptedPassword = passwordEncoder.encode(empDto.getPassword());
            employee.setPassword(encryptedPassword);
            
            employee.setDepartment(department); 
            return employee;
        }).collect(Collectors.toList());

        department.setEmployees(employees);

       
        return departmentRepository.save(department);
    }

   
}
