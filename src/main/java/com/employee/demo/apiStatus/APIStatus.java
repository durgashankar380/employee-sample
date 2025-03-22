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
    EMPLOYEE_STATUS_INVALID(404, "The entered status is invalid !!!"),
    EMPLOYEE_INCORRECT_PREVIOUS_PASSWORD(401, "Incorrect previous Password!!!"),
    EMPLOYEE_INCORRECT_NEW_CONFORM_PASSWORD(401, "New Password and Confirm Password do not match!!!");

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

