package com.employee.demo.response;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePageResponse{
	 private List<ResponseEmployee> content;
	    private Pageable pageable;
	    private long totalElements;
	    private boolean last;
	    private int totalPages;
	    private int size;
	    private int number;
	    private Sort sort;
	    private boolean first;
	    private int numberOfElements;
	    private boolean empty;

	
}