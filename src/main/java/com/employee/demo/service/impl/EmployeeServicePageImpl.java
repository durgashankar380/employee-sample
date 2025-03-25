package com.employee.demo.service.impl;

import com.employee.demo.apiResponse.ApiResponse;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.request.EmployeePageRequest;
import com.employee.demo.service.EmployeeServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServicePageImpl implements EmployeeServicePage {
    @Autowired
    private EmployeeRepository repository;


    @Override
    public EmployeePageResponse getEmployeePage(EmployeePageRequest request) {
        String sortBy=request.getSortDir();
        System.out.println(sortBy);
        Sort.Direction direction = sortBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        int size=request.getPageSize();
        if(request.getPageSize()<=0){
            size=repository.findAll().size();
        }
        Pageable pageable= (Pageable) PageRequest.of(request.getPageNumber(),size,Sort.by(direction,request.getSortBy()));
        Page<Employee> employees;
        if(!request.getSearchBy().isEmpty() && !request.getSearchBy().isBlank()){
//            if(request.getNumSearch()=>Integer.MIN_VALUE && request.getNumSearch()<=Integer.MAX_VALUE)
            employees=repository.search(request.getSearchBy(),pageable);
        }else{
            employees=repository.findAll(pageable);
        }
        List<EmployeeResponse> responseList=employees.getContent().stream().
//                filter(employee -> employee.getStatus()==1).
                map(EmployeeResponse::new).
                toList();
        return new EmployeePageResponse(
                employees
        );
    }

    @Override
    public ResponseEntity<?> updateStatus(Long id, int status) {
        Employee employee=repository.findById(id).orElse(null);
        int prevStatus=0;
        if(employee==null){
            return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_NOT_FOUND);
        }else if(status<1 || status>3){
            return ResponseEntity.badRequest().body(ApiResponse.EMPLOYEE_INVALID_STATUS);
        }
        else if(employee.getStatus()==status){
            String state= status==1?" ACTIVE ":(status==2?" INACTIVE " : " TEMPORARILY DELETE ");
            String response = "NO NEED TO UPDATE THE STATUS IT IS ALREADY " + state;
            return ResponseEntity.badRequest().body(response);
        }
        else{

            prevStatus=employee.getStatus();
            String previous= prevStatus==1?" ACTIVE ":(prevStatus==2?" INACTIVE " : " TEMPORARILY DELETE ");
            String current= status==1?" ACTIVE ":(status==2?" INACTIVE " : " TEMPORARILY DELETE ");
            employee.setStatus(status);
            employee = repository.save(employee);
            return ResponseEntity.ok("YOUR STATUS IS UPDATED SUCCESSFULLY FROM "+
                    previous +" TO "+current+ " AT EMPLOYEE ID "+employee.getId());
        }



//        return new ResponseEntity<>( new EmployeeResponse(employee.getId(),
//                employee.getName(),
//                employee.getDepartment(),
//                employee.getSalary(),employee.getStatus()), HttpStatus.ACCEPTED);

    }

    @Override
    public EmployeePageResponse getPageByNameDepartmentSalary(String name, String department, Double salary, EmployeePageRequest request) {
        int size=repository.findAll().size();
        Pageable pageable;
        Sort.Direction sort = request.getSortDir().equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
        if (request.getPageSize()<=0) {
            pageable = PageRequest.of(0,size,sort,request.getSortBy());
        }
        else {
            pageable = PageRequest.of(request.getPageNumber(),request.getPageSize(),sort,request.getSortBy());
        }
        Page<Employee> employees =repository.findByNameAndDepartmentAndSalary(name,department,salary,pageable);
        List<EmployeeResponse> responseList=employees.getContent().stream()
//                .filter(employee -> employee.getStatus()==1)
                .map(EmployeeResponse::new).toList();
        return new EmployeePageResponse(employees);
    }

    @Override
    public EmployeePageResponse getEmployeeAllPage(EmployeePageRequest request) {
        int size=repository.findAll().size();
        Pageable pageable;
        Sort.Direction sort = request.getSortDir().equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
        if (request.getPageSize()<=0) {
            pageable = PageRequest.of(0,size,sort,request.getSortBy());
        }
        else {
            pageable = PageRequest.of(request.getPageNumber(),request.getPageSize(),sort,request.getSortBy());
        }
        Page<Employee> employees =repository.findAllByStatus(pageable);
        List<EmployeeResponse> responseList=employees.getContent().stream()
//                .filter(employee -> employee.getStatus()==1)
                .map(EmployeeResponse::new).toList();
        return new EmployeePageResponse(employees);
    }

    @Override
    public ResponseEntity<?> searchByStatus(EmployeePageRequest request) {
        int status=request.getStatus();
        int size=request.getPageSize();
        if(request.getPageSize()==0){
            size=repository.findAll().size();
        }
        Pageable pageable=PageRequest.of(request.getPageNumber(),size,Sort.by(
                request.getSortDir().equalsIgnoreCase("desc")?
                        Sort.Direction.DESC:Sort.Direction.ASC ,
                request.getSortBy()));
        Page<Employee> employees;
        if(status==0){
            employees=repository.findByStatusNot(3,pageable);
        }else{
            employees=repository.findByStatus(status,pageable);
        }
        List<EmployeeResponse> responseList=employees.getContent().stream().
//                filter(employee -> employee.getStatus()==1).
                map(EmployeeResponse::new).toList();
        return new ResponseEntity<>(new EmployeePageResponse(
                employees
        ),HttpStatus.OK);
    }


}
