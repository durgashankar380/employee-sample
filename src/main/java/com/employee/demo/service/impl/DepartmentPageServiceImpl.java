package com.employee.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.employee.demo.Specification.DepartmentSpecification;
import com.employee.demo.model.Department;
import com.employee.demo.repository.DepartmentRepository;
import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.service.DepartmentPageService;

@Service
public class DepartmentPageServiceImpl implements DepartmentPageService {
	 @Autowired
	    private DepartmentRepository departmentRepository;

	    public Page<Department> searchDepartments(DepartmentPageRequest request) {
	        Specification<Department> spec = DepartmentSpecification.filterByCriteria(request.getKeyword(), request.getStatus());

	        // Sorting logic
	        Sort sort = request.getSortDirection().equalsIgnoreCase("desc")
	                ? Sort.by(request.getSortBy()).descending()
	                : Sort.by(request.getSortBy()).ascending();
	        
	        // Pagination
	        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
	        
	        return departmentRepository.findAll(spec, pageable);
	    }
	}
