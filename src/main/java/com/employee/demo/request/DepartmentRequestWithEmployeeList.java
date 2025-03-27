package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestWithEmployeeList {
    private Long id;
    private String name;
    private String location;
    private String description;
    private List<EmployeeRequestList> employeeRequestList;
}
