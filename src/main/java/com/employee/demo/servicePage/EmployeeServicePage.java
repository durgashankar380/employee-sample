package com.employee.demo.servicePage;

import com.employee.demo.model.Employee;
import com.employee.demo.requestPage.EmployeePageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface EmployeeServicePage {
 Page<Employee> searchEmployee(EmployeePageRequest request);

}
