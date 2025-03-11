package com.employee.demo.request;

import lombok.Data;

@Data
public class EmployeePageRequest {

    private Integer pageIndex;
    private Integer pageSize;
    private String sortBy;
    private String sortDir;
    private String searchBy;
    private Integer status;


}
