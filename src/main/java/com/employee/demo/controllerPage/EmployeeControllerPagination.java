package com.employee.demo.controllerPage;

import com.employee.demo.model.Employee;
import com.employee.demo.requestPage.EmployeePageRequest;
import com.employee.demo.servicePage.EmployeeServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

   @GetMapping("/searchEmp")
   public List<Employee> search(@RequestParam("searchBy") String searchBy){
    return servicePage.search(searchBy);
   }
   @GetMapping("/searchByName")
   public Page<Employee> searchByName(@RequestBody EmployeePageRequest request){
        return servicePage.searchByName(request);
   }

}
