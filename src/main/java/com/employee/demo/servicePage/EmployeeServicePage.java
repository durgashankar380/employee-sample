package com.employee.demo.servicePage;

import com.employee.demo.responsePage.EmployeePageResponse;
import com.employee.demo.resquestPage.EmployeePageRequest;

public interface EmployeeServicePage {
    EmployeePageResponse getEmployeePage(EmployeePageRequest request);
}
