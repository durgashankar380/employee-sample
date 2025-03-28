package com.employee.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.model.Department;
import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService service;
	
	@PostMapping("/add-or-update")
	public String createDepartment(@RequestBody DepartmentRequest departmentRequest) {
		return service.addOrUpdateDepartment(departmentRequest);
	}
	
	@GetMapping("/all")
	public List<DepartmentResponse> getAllDepartments() {
		return service.getAllDepartments();
	}
	
	@PostMapping("/get-all-pagination")
	public Page<DepartmentResponse> getAllByDepartment(@RequestBody DepartmentPageRequest departmentPageRequest) {
		return service.getAllByPagination(departmentPageRequest);	
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
		Department response = service.getDepartmentByName(name);
		if(response == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(response); 
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Department response = service.getDepartmentById(id);
		if(response == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(response);	
	}
	
	@PutMapping("/update/{id}")
	public String updateDepartment(@PathVariable Long id, @RequestBody Department department) {
		return service.updateDepartment(id,department);	
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteDepartment(@PathVariable Long id) {
		return service.deleteDepartment(id);
	}
	
	
}
