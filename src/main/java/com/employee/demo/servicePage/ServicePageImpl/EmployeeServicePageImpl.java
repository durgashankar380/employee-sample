package com.employee.demo.servicePage.ServicePageImpl;


import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.requestPage.EmployeePageRequest;
import com.employee.demo.servicePage.EmployeeServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServicePageImpl implements EmployeeServicePage {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public Page<Employee> searchEmployee(EmployeePageRequest request){
        Sort.Direction direction =request.getSortDir().equalsIgnoreCase("desc")?Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable= PageRequest.of(request.getPageIndex(),request.getPageSize(), Sort.by(direction,request.getSortBy()));
        return repository.findAll(pageable);
    }

@Override
    public List<Employee> search(String searchBy){

        return repository.findByNameLikeIgnoreCase("%" + searchBy +"%");
}

    @Override
    public Page<Employee> searchByName(EmployeePageRequest request) {
        Pageable pageable=PageRequest.of(request.getPageIndex(),request.getPageSize(),Sort.by(Sort.Direction.fromString(request.getSortDir()),request.getSortBy()));

        return repository.findByNameContainingIgnoreCase(request.getSearchBy(),pageable);
    }
}
