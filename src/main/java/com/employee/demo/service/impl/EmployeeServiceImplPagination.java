package com.employee.demo.service.impl;

import java.util.List;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepositoryPagination;
import com.employee.demo.service.EmployeeServicePagination;

import dto.EmployeePageRequestDto;

@Service
public class EmployeeServiceImplPagination implements EmployeeServicePagination {


		private final EmployeeRepositoryPagination employeeRepositoryPagination;
	
	public EmployeeServiceImplPagination(EmployeeRepositoryPagination employeeRepositoryPagination) {
		this.employeeRepositoryPagination = employeeRepositoryPagination;
	}

	// retrieve all employees using pagination
	
	@Override
	public Page<Employee> getAllEmployeeUsingPagination(EmployeePageRequestDto dto) {
		Pageable pageable = dto.getPageable();
		return employeeRepositoryPagination.findAll(pageable);
	}
	
	// retrieves list of employees using pagination and sorting
	
	@Override
	public Page<Employee> getAllEmployeeUsingPaginationList(EmployeePageRequestDto dto) {
		// TODO Auto-generated method stub
		 List<Employee> employeeList = employeeRepositoryPagination.findAll();
	        
	        PagedListHolder<Employee> pageListHolder = new PagedListHolder<>(employeeList);
	        pageListHolder.setPage(dto.getPageNo());
	        pageListHolder.setPageSize(dto.getPageSize());

	        boolean ascending = dto.getSort().isAscending();
	        List<Employee> pageSlice = pageListHolder.getPageList();
	        PropertyComparator.sort(pageSlice, new MutableSortDefinition(dto.getSortByColumn(), true, ascending));

	        Pageable pageable = dto.getPageable();
	        return new PageImpl<>(pageSlice, pageable, employeeList.size());
	}
}
