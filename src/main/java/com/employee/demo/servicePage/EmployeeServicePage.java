package com.employee.demo.servicePage;

import com.employee.demo.model.Employee;
import com.employee.demo.requestPage.EmployeePageRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeServicePage {
 Page<Employee> searchEmployee(EmployeePageRequest request);

  List<Employee> search(String searchBy);

 Page<Employee> searchByName(EmployeePageRequest request);
}

//irctc partioning and shardining