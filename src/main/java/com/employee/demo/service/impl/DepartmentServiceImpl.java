package com.employee.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired
	private DepartmentRepository repository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

//	@Override
//	@Transactional
//	public String addOrUpdateDepartment(Department department) {		
//		if(department.getId() != 0 ) {
//			Department existingDepartment = repository.findById(department.getId()).orElse(null);
//			if(existingDepartment == null) {
//				return "Department not found.";
//			}
//			existingDepartment.setName(department.getName());
//			existingDepartment.setLocation(department.getLocation());
//			existingDepartment.setDescription(department.getDescription());
//			existingDepartment.setStatus(department.getStatus());
//			
//			List<Employee> employees = department.getEmployee();
//			for(Employee emp : employees) {
//				
//				if(emp.getId() != 0) {
//					Employee existingEmployee = employeeRepository.findById(emp.getId()).orElse(null);
//					if(existingEmployee == null) {
//						return "Employee not found";
//					}
//					existingEmployee.setName(emp.getName());
//					existingEmployee.setEmailId(emp.getEmailId());
//					existingEmployee.setSalary(emp.getSalary());
//					existingEmployee.setPassword(passwordEncoder.encode(emp.getPassword()));
//					existingEmployee.setDepartment(existingDepartment.getName());
//					existingEmployee.setDepartments(existingDepartment);
//					existingEmployee.setStatus(emp.getStatus());
//					
//					employeeRepository.save(existingEmployee);
//				}else {
//					emp.setDepartments(existingDepartment);
//					emp.setPassword(passwordEncoder.encode(emp.getPassword()));
//					emp.setDepartment(existingDepartment.getName());
//					emp.setStatus(1);
//					employeeRepository.save(emp);
//				}
//			}
//				repository.save(existingDepartment);
//				return "Department and Employee updated successfully";
//				}else {
//			        // Create new department
//			        for (Employee emp : department.getEmployee()) {
//			            emp.setDepartments(department);
//			            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
//			            emp.setDepartment(department.getName());
//			            emp.setStatus(1);
//			        }
//			        repository.save(department);
//			        return "New Department and Employees Created.";
//			    }
//		}
	
	
	@Override
	@Transactional
	public String addOrUpdateDepartment(DepartmentRequest departmentRequest) {
		Department department;
 
		// If Department ID is not zero, update existing department
		if (departmentRequest.getId() != 0) {
			department = repository.findById(departmentRequest.getId()).orElse(null);
			if (department == null) {
				return "Department does not exist with this DepartmentId.";
			}
		} else {
			// If ID is 0, create a new department
			department = new Department();
			department.setStatus(1); // Default active status
		}
 
		department.setName(departmentRequest.getName());
		department.setLocation(departmentRequest.getLocation());
		department.setDescription(departmentRequest.getDescription());
		department.setStatus(departmentRequest.getStatus());
 
		List<Employee> employees = new ArrayList<>();
		for (RequestEmployee empReq : departmentRequest.getEmployee()) {
			Employee employee;
 
			// If employee ID is not zero, update existing employee
			if (empReq.getId() != 0) {
				employee = employeeRepository.findById(empReq.getId()).orElse(null);
				if (employee == null) {
					return "Employee not found";
				}
			} else {
				// Otherwise, create a new employee
				employee = new Employee();
			}
 
			employee.setName(empReq.getName());
			employee.setDepartment(departmentRequest.getName());
			employee.setStatus(departmentRequest.getStatus());
			employee.setEmailId(empReq.getEmailId());
			employee.setSalary(empReq.getSalary());
			employee.setPassword(passwordEncoder.encode(empReq.getPassword()));
			employee.setDepartments(department); // Associate with department
 
			employees.add(employee);
		}
 
		department.setEmployee(employees);
		repository.save(department);
		employeeRepository.saveAll(employees);
 
		return departmentRequest.getId() != 0 ? "Existing Department and Employees updated successfully"
				: "New Department and Employees added successfully.";
	}
 
	
//	@Override
//	public List<DepartmentResponse> getAllDepartments() {
//		return repository.findAll();
//	}
	
	@Override
	public List<DepartmentResponse> getAllDepartments() {
	    List<Department> allDepartments = repository.findAll();
	    return allDepartments.stream()
	                         .map(DepartmentResponse::new) // Convert each Department to DepartmentResponse
	                         .collect(Collectors.toList());
	}
	
	@Override
	public Department getDepartmentByName(String name) {
		// TODO Auto-generated method stub
		Department dept = repository.findByName(name).orElse(null);
		return dept;
	}
	
	@Override
	public Department getDepartmentById(Long id) {
		// TODO Auto-generated method stub
		Department dept = repository.findById(id).orElse(null);
		return dept;
	}
	
	@Override
	public String updateDepartment(Long id, Department newDepartment) {
		// TODO Auto-generated method stub
		Department oldDepartment = repository.findById(id).orElse(null);
		if(oldDepartment == null) {
			throw new RuntimeException("Department not found");
		}
		
		oldDepartment.setName(newDepartment.getName());
		oldDepartment.setLocation(newDepartment.getLocation());
		oldDepartment.setDescription(newDepartment.getDescription());
		oldDepartment.setStatus(newDepartment.getStatus());
		
		repository.save(oldDepartment);
		return "Department updated successfully";
	}
	
	@Override
	public String deleteDepartment(Long id) {
		// TODO Auto-generated method stub
		Department dept = repository.findById(id).orElse(null);
		if(dept == null) {
			throw new RuntimeException("Department with id not found");
		}
		repository.deleteById(id);
		return "Department deleted successfully";
	}


	@Override
	public Page<DepartmentResponse> getAllByPagination(DepartmentPageRequest request) {
	    int pageNumber = Optional.ofNullable(request.getPageNo()).orElse(0); // Default to page 0
	    int pageSize = Optional.ofNullable(request.getPageSize()).orElse(10); // Default page size
 
	    String keyword = request.getKeyword();
	    if (keyword != null && keyword.trim().isEmpty()) {
	        keyword = null; // Handle empty keyword as null
	    }
 
	    String sortBy = Optional.ofNullable(request.getSortBy())
	                            .filter(s -> !s.trim().isEmpty()) // Ensure it's not empty
	                            .orElse("id"); // Default sorting field
 
	    String sortDir = Optional.ofNullable(request.getSortDir())
	                             .filter(s -> s.equalsIgnoreCase("asc") || s.equalsIgnoreCase("desc")) // Ensure it's valid
	                             .orElse("desc"); // Default sorting order
 
	    int status = Optional.ofNullable(request.getStatus()).orElse(0); // Default status
 
	    Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
	    Sort sort = Sort.by(direction, sortBy);
 
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    
	    Page<Department> departmentPage = repository.searchDepartments(keyword, status, pageable);
	    
	    return departmentPage.map(DepartmentResponse::new); // Convert Page<Department> to Page<DepartmentResponse>
	}
 

}
