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

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.request.RequestEmployee;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.service.DepartmentServices;

import jakarta.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentServices {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * if depertment ID is 0 then add new Department and If Not 0 then add new
	 * Department. as same for department's field Object type employee.
	 **/
	@Override
	@Transactional
	public String addOrUpdateDepartment(DepartmentRequest departmentRequest) {
		Department department;

		// If Department ID is not zero, update existing department
		if (departmentRequest.getDepartmentId() != 0) {
			department = departmentRepository.findByDepartmentId(departmentRequest.getDepartmentId()).orElse(null);
			if (department == null) {
				return "Department does not exist with this DepartmentId.";
			}
		} else {
			// If ID is 0, create a new department
			department = new Department();
			department.setStatus(1); // Default active status
		}

		department.setDepartmentName(departmentRequest.getDepartmentName());
		department.setLocation(departmentRequest.getLocation());
		department.setDescription(departmentRequest.getDescription());
		department.setStatus(departmentRequest.getStatus());

		List<Employee> employees = new ArrayList<>();
		for (RequestEmployee empReq : departmentRequest.getEmployees()) {
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
			employee.setDepartment(departmentRequest.getDepartmentName());
			employee.setStatus(departmentRequest.getStatus());
			employee.setEmailId(empReq.getEmailId());
			employee.setSalary(empReq.getSalary());
			employee.setPassword(passwordEncoder.encode(empReq.getPassword()));
			employee.setDepartments(department); // Associate with department

			employees.add(employee);
		}

		department.setEmployees(employees);
		departmentRepository.save(department);
		employeeRepository.saveAll(employees);
		return departmentRequest.getDepartmentId() != 0 ? "Existing Department and Employees updated successfully"
				: "New Department and Employees added successfully.";
	}

// Get All Employee
	@Override
	public List<DepartmentResponse> getAllDepartment() {
		List<Department> allDepartments = departmentRepository.findAll();
		return allDepartments.stream().map(DepartmentResponse::new) // Convert each Department to DepartmentResponse
				.collect(Collectors.toList());
	}

// Get all employee with pagination.
	@Override
	public Page<DepartmentResponse> getAllByPagination(DepartmentPageRequest request) {
		int pageNumber = Optional.ofNullable(request.getPageNumber()).orElse(0);
		int pageSize = Optional.ofNullable(request.getPageSize()).orElse(10);

		String keyword = Optional.ofNullable(request.getKeyword()).filter(k -> !k.trim().isEmpty()).orElse(null);
		String sortBy = Optional.ofNullable(request.getSortBy()).filter(s -> !s.trim().isEmpty())
				.orElse("departmentId");
		String sortDir = Optional.ofNullable(request.getSortDir())
				.filter(s -> s.equalsIgnoreCase("asc") || s.equalsIgnoreCase("desc")).orElse("desc");

		int status = Optional.ofNullable(request.getStatus()).orElse(0);

		Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sort = Sort.by(direction, sortBy);

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Department> departmentPage = departmentRepository.findDepartmentByKeywordAndStatus(keyword, status,
				pageable);

		return departmentPage.map(DepartmentResponse::new); // Convert Page<Department> to Page<DepartmentResponse>
	}



// Delete Employee by DepartmentId.
	@Override
	public String deleteDepartment(Integer departmentId) {
		Department depart = departmentRepository.findById(departmentId).orElse(null);
		if (depart == null) {
			return "No department exist with this id.";
		}
		departmentRepository.deleteById(departmentId);
		return "Employee deleted Succesfully.";
	}

}