package com.employee.demo.service.impl;


import com.employee.demo.apiStatus.APIStatus;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.service.EmployeeServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServicePageImpl implements EmployeeServicePage {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public Page<Employee> getAllEmployeePage(EmployeePageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), Sort.by(Sort.Direction.fromString(request.getSortDir()), request.getSortBy()));
        Page<Employee> employees;
        int size = request.getPageSize();
        if (size >= 0) {
            if (!request.getSearchBy().isEmpty() && !request.getSearchBy().isBlank()) {
                employees = repository.search(request.getSearchBy(), pageable);
            } else {
                employees = repository.findAll(pageable);
            }
            return employees;
        } else {
            pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.fromString(request.getSortDir()), request.getSortBy()));
            return (Page<Employee>) pageable;
        }
    }


    @Override
    public EmployeePageResponse findByNameAndSalaryAndDepartment(String name, double salary, String department, EmployeePageRequest request) {
        int size = repository.findAll().size();
        Pageable pageable;
        Sort.Direction sort = request.getSortDir().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        if (request.getPageSize() <= 0) {
            pageable = PageRequest.of(0, size, sort, request.getSortBy());
        } else {
            pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(), sort, request.getSortBy());
        }
        Page<Employee> employees = repository.findByNameAndSalaryAndDepartment(name, salary, department, pageable);

        List<EmployeeResponse> responses = employees.getContent().stream().
                map(EmployeeResponse::new).collect(Collectors.toList());

        return new EmployeePageResponse(employees);

    }


    @Override
    public ResponseEntity<?> updateStatus(int status, long employeeId) {
        Employee employee = repository.findById(employeeId).orElse(null);
        int prev = 0;
        if (employee == null) {
            return ResponseEntity.badRequest().body(APIStatus.EMPLOYEE_NOT_FOUND);
        } else if (status < 1 || status > 3) {
            return ResponseEntity.badRequest().body(APIStatus.EMPLOYEE_INVALID_STATUS);
        } else if (employee.getStatus() == status) {
            String s = status == 1 ? "ACTIVE" : (status == 2) ? " INACTIVE " : " Temporarily DELETED ";
            String resp = "No need to update " + s;
            return ResponseEntity.badRequest().body(resp);
        } else {
            prev = employee.getStatus();
            employee.setStatus(status);
            employee = repository.save(employee);
        }
        return new ResponseEntity<>(new EmployeeResponse(employee), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> searchByStatus(EmployeePageRequest request) {
        int status=request.getStatus();
        int size = request.getPageSize();
        if (request.getPageSize() == 0) {
            size =repository.findAll().size();
        }
        Pageable pageable = PageRequest.of(request.getPageIndex(),size, Sort.by(request.getSortDir().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, request.getSortBy()));
        Page<Employee> employees;
        if (status == 0) {
            employees = repository.findByStatusNot(3, pageable);
        }else if(status>3 || status<1){
            //return ResponseEntity.badRequest().body("the entered status is invalid ");
            return ResponseEntity.badRequest().body(APIStatus.EMPLOYEE_STATUS_INVALID);
        }
        else {
            employees = repository.findByStatus(status, pageable);
        }
        List<EmployeeResponse> responseList = employees.getContent().stream().map(EmployeeResponse::new).toList();

        return new ResponseEntity<>(new EmployeePageResponse(
                employees
        ), HttpStatus.OK);
    }


}
