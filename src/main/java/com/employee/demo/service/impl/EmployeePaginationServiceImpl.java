package com.employee.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeePaginationRepository;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.request.EmployeeRequest;
import com.employee.demo.service.EmployeePaginationService;

@Service
public class EmployeePaginationServiceImpl implements EmployeePaginationService {

	@Autowired
	private EmployeePaginationRepository repository;

//	get List of all Employee with sorting by any field.
	@Override
	public List<Employee> findEmployeeWithSorting(String field) {
		return repository.findAll(Sort.by(Sort.Direction.DESC, field));
	}

//	get employee using pagination.
	@Override
	public Page<Employee> findEmployeeWithPagination(EmployeePageRequest request) {
		Page<Employee> pageable = repository
				.findAll(PageRequest.of(request.getPageNumber() - 1, request.getPageSize()));
		int count = repository.findAll().size();
		if (request.getPageNumber() == 0) {
			return repository.findAll(PageRequest.ofSize(count));
		}
		return pageable;
	}

//	get employee using pagination and sorting.
	@Override
	public Page<Employee> findEmployeeWithPaginationAndSorting(EmployeePageRequest request) {
		Page<Employee> pageable = repository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize(),
				Sort.by(Sort.Direction.DESC, request.getSortBy())));
		return pageable;
	}

//	get employee using pagination and searching.	
	@Override
	public Page<Employee> searchEmployees(EmployeePageRequest request) {
		Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
		Page<Employee> employeePage = repository.searchEmployees(request.getSalary(), request.getKeyword(), pageable);
		return employeePage;
	}

// getting employee by their status.
	@Override
	public Page<Employee> getEmployees(EmployeePageRequest request) {
		Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
		if (request.getStatus() != 0) {
			Page<Employee> page = repository.findByStatus(request.getStatus(), pageable);
			return page;
		}
		Page<Employee> page = repository.findByStatusNot(3, pageable);
		return page;
	}

// Manage employee Status.
	@Override
	public String updateStatus(long id, int status) {
		try {
			if (status < 4 && status > 0) {
				Employee employee = repository.findById(id).orElseThrow();
				employee.setStatus(status);
				repository.save(employee);
				if (status == 1) {
					return "Employee activate succesfully.";
				} else if (status == 2) {
					return "Employee deactivate succesfully.";
				} else if (status == 3) {
					return "Employee deleted succesfully.";
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Invalid request, please give valid id or status.";
	}

	@Override
	public String addOrUpdateWithId(EmployeeRequest request) {
		Employee emp = repository.findById(request.getId()).orElse(null);
		if (request.getId() == 0) {
			Employee newEmp = new Employee();
			newEmp.setName(request.getName());
			newEmp.setDepartment(request.getDepartment());
			newEmp.setSalary(request.getSalary());
			newEmp.setStatus(1);
			repository.save(newEmp);
			return "New Employee Added Succesfully.";
		}else if(emp != null) {
			emp.setName(request.getName());
			emp.setSalary(request.getSalary());
			emp.setDepartment(request.getDepartment());
			emp.setStatus(1);
			repository.save(emp);
			return "Employee Updated succesfully.";	
		} else {
			return "Employee Not existed with this id.";
		}
		
		
	}

}
