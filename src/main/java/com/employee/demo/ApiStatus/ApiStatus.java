package com.employee.demo.ApiStatus;

public enum ApiStatus {
	
	EMPLOYEE_NOT_FOUND(404,"Employee with given ID not found"),
	EMPLOYEE_ADDED_SUCCESSFULLY(200,"Employee added successfully"),
	EMPLOYEE_UPDATED_SUCCESSFULLY(200,"Employee updated successfully"),
	EMPLOYEE_DOES_NOT_EXIST(404, "Employee does not exist with this ID"),
    EMPLOYEE_ACTIVATED(200, "Employee activated successfully"),
    EMPLOYEE_INACTIVATED(200, "Employee inactivated successfully"),
    EMPLOYEE_DELETED(200, "Employee deleted successfully"),
    INVALID_STATUS(400, "Invalid status"),
    NO_EMPLOYEES_FOUND(404, "No employees found");
	
	
	private final int statusCode;
	private final String message;
	
	
	ApiStatus(int statusCode, String message) {
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
