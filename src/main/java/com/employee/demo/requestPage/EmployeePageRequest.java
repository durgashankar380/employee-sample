package com.employee.demo.requestPage;

import lombok.Data;

@Data
public class EmployeePageRequest {

    private Integer pageIndex;
    private Integer pageSize;
    private String sortBy;
    private String sortDir;
    private String SearchBy;


}
//