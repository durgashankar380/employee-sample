package com.employee.demo.Exception;

import com.employee.demo.ApiStatus.ApiStatus;

public class EmployeeSaveException extends RuntimeException {
	  private final int statusCode;
	public EmployeeSaveException(ApiStatus apiStatus) {
        super(apiStatus.getMessage());
        this.statusCode = apiStatus.getStatusCode();
    }
	
	 public int getStatusCode() {
	        return statusCode;
	    }

}
