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
import com.employee.demo.service.EmployeePaginationService;

@Service
public class EmployeePaginationServiceImpl implements EmployeePaginationService {

	@Autowired
	private EmployeePaginationRepository repository;

	// get List of all Employee.
	@Override
	public List<Employee> getAllEmployee() {
		return repository.findAll();
	}

//	get List of all Employee with sorting by any field.
	@Override
	public List<Employee> findEmployeeWithSorting(String field) {
		return repository.findAll(Sort.by(Sort.Direction.DESC, field));
	}

//	get employee using pagination.
	@Override
	public Page<Employee> findEmployeeWithPagination(EmployeePageRequest request){
		Page<Employee> pageable = repository.findAll(PageRequest.of(request.getPageNumber()-1,request.getPageSize()));
		int count = repository.findAll().size();
		if(request.getPageNumber() == 0) {
			return repository.findAll(PageRequest.ofSize(count));
		}
		return pageable;
	}

//	get employee using pagination and sorting.
	@Override
	public Page<Employee> findEmployeeWithPaginationAndSorting(EmployeePageRequest request) {
		Page<Employee> pageable = repository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(Sort.Direction.DESC,request.getSortBy())));
		return pageable;
	}
	
//	get employee using pagination and searching.	
	@Override
	public Page<Employee> searchEmployees(EmployeePageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<Employee> employeePage = repository.searchEmployees(request.getSalary(), request.getKeyword(), pageable);
        return employeePage;
    }

}
