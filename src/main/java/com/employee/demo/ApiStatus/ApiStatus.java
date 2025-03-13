package com.employee.demo.ApiStatus;

public enum ApiStatus {

	    DATA_ACTIVATED_SUCCESSFULLY(200, "Data activated successfully"),
	    DATA_INACTIVATED_SUCCESSFULLY(200, "Data inactivated successfully"),
	    DATA_DELETED_SUCCESSFULLY(200, "Data deleted successfully"),
	    INVALID_STATUS(400, "Invalid status"),
	    EMPLOYEE_NOT_FOUND(404, "Employee not found"),
	    NEW_EMPLOYEE_ADDED_SUCCESSFULLY(201, "New Employee added successfully"),
	    EMPLOYEE_STATUS_UPDATED(200, "Employee status updated");
	    
	
	
	private final int statusCode;
	private final String message;
	
	
	    private ApiStatus(int statusCode, String message) {
	    	this.statusCode = statusCode;
	    	this.message = message;
	    }
	    
	    public int getStatusCode() {
	        return statusCode;
	    }

	    public String getMessage() {
	        return message;
	    }
	
}
