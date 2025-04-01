package com.employee.demo.apiStatus;

import lombok.Getter;


@Getter
public enum APIStatus {
    EMPLOYEE_FILE_DATA_SAVE(200, "Excel file Data saved into DataBase!!!"),
    EMPLOYEE_PASSWORD_SET(200, "Password Set Successfully!!!"),
    EMPLOYEE_PASSWORD_RESET(200, "Password Reset Successfully!!!"),
    EMPLOYEE_INVALID_FILE_FORMAT(404, "FILE FORMAT IS WRONG!!!"),
    EMPLOYEE_INVALID_ID(404, "No Employee with this Id!!!"),
    EMPLOYEE_INVALID_STATUS(404, "Invalid status to update!!!"),
    EMPLOYEE_NOT_FOUND(404, "Employee doesn't Exist !!!"),
    EMAIL_NOT_FOUND(404, "Employee with this Email is not found !!!"),
    DEPARTMENT_STATUS_ACTIVATED_SUCCESSFULLY(200, "Department Status Activated Successfully!!"),
    DEPARTMENT_STATUS_DEACTIVATED_SUCCESSFULLY(200, "Department Status DeActivated Successfully!!"),
    DEPARTMENT_STATUS_TEMPORARILY_DELETED_SUCCESSFULLY(200, "Department Status temporarily deleted Successfully!!"),
    INVALID_DEPARTMENT_ID(404, "Invalid Department Id !!"),
    INVALID_STATUS(404, "Invalid Status!!"),
    DEPARTMENT_BAD_REQUEST(400, "At least one employee must be provided!!"),
    EMPLOYEE_BAD_REQUEST(400, "Employee name, email, and password cannot be null!!"),
    EMPLOYEE_STATUS_INVALID(404, "The entered status is invalid !!!"),
    EMPLOYEE_INCORRECT_PREVIOUS_PASSWORD(401, "Incorrect previous Password!!!"),
    EMPLOYEE_INCORRECT_NEW_CONFORM_PASSWORD(401, "New Password and Confirm Password do not match!!!"),
    EMAIL_NOT_SEND(401, "Password changed, but email sending failed:!");

    private final int code;
    private final String message;

    APIStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

