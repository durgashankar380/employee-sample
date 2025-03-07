package com.employee.demo.request;

import lombok.Data;

@Data
public class EmployeePagginationReq {

	private Integer pageIndex;

    private Integer pageSize;

    private String sortBy;

    private String searchBy;

    private String sortingOrder;
}
