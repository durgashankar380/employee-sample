package com.employee.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.request.DepartmentPageRequest;
import com.employee.demo.request.DepartmentRequest;
import com.employee.demo.response.DepartmentResponse;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.service.DepartmentServices;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentServices departmentService;

	@PostMapping("/add-or-update")
	String addDepartment(@RequestBody DepartmentRequest departmentRequest) {
		return departmentService.addOrUpdateDepartment(departmentRequest);
	}

	@PostMapping("/pagination-all")
	public EmployeePageResponse<Page<DepartmentResponse>> getEmployeesWithPagination(
			@RequestBody DepartmentPageRequest request) {
		Page<DepartmentResponse> departmentPage = departmentService.getAllByPagination(request);
		return new EmployeePageResponse<>(departmentPage.getContent().size(),
				departmentPage.getContent().isEmpty() ? "no record found !!" : "data found succesfully.",
				departmentPage);
	}

	@GetMapping("/all")
	List<DepartmentResponse> getAllDepartment() {
		return departmentService.getAllDepartment();
	}

	@DeleteMapping("/delete-by-id")
	String deleteDepartment(@RequestParam Integer departmentId) {
		return departmentService.deleteDepartment(departmentId);
	}

}
