package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestWithList {
        private Long id;
        private String name;
        private String location;
        private String description;
        private List<EmployeeRequest> employeeRequestList;

}
