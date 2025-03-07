package com.employee.demo.service;

import com.employee.demo.response.EmployeePageResponse;
import com.employee.demo.request.EmployeePageRequest;

public interface EmployeeServicePage {
    EmployeePageResponse getEmployeePage(EmployeePageRequest request);
}
