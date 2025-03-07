package com.employee.demo.servicePage.implimentation;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.responsePage.EmployeePageResponse;
import com.employee.demo.resquestPage.EmployeePageRequest;
import com.employee.demo.servicePage.EmployeeServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
public class EmployeeServicePageImpl implements EmployeeServicePage {
    @Autowired
    private EmployeeRepository repository;


    @Override
    public EmployeePageResponse getEmployeePage(EmployeePageRequest request) {
        Sort.Direction direction = request.getSortDir().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable= (Pageable) PageRequest.of(request.getPageSize(),request.getPageIndex(),Sort.by(direction,request.getSortBy()));
        Page<Employee> employees;
        if(!request.getSearchBy().isEmpty() && !request.getSearchBy().isBlank()){
//            if(request.getNumSearch()=>Integer.MIN_VALUE && request.getNumSearch()<=Integer.MAX_VALUE)
            employees=repository.search(request.getSearchBy(),pageable);
        }else{
            employees=repository.findAll(pageable);
        }
        List<EmployeeResponse> responseList=employees.getContent().stream().
                map(employee -> new EmployeeResponse(employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary())).
                toList();
        return new EmployeePageResponse(
                responseList,
                employees.getTotalPages(),
                employees.getNumber(),
                employees.getPageable(),
                employees.getSize(),
                employees.getNumberOfElements(),
                employees.getTotalElements(),
                employees.getSort(),
                employees.isFirst(),
                employees.isLast(),
                employees.isEmpty()
        );
    }
}
