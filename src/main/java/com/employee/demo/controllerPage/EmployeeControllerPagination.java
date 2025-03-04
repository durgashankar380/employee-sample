package com.employee.demo.controllerPage;

import com.employee.demo.model.Employee;
import com.employee.demo.requestPage.EmployeePageRequest;
import com.employee.demo.response.EmployeeResponse;
import com.employee.demo.servicePage.EmployeeServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/page")
public class EmployeeControllerPagination {

    @Autowired
    private EmployeeServicePage servicePage;

    @GetMapping("/search")
    public Page<Employee> searchEmployee(@RequestBody EmployeePageRequest request){
       return servicePage.searchEmployee(request);
    }


}
