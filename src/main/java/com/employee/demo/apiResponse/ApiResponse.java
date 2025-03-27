package com.employee.demo.apiResponse;

import lombok.Getter;


@Getter
public enum ApiResponse {
   EMPLOYEE_NOT_FOUND(404,"Employee doesn't exist !! "),
   EMPLOYEE_INVALID_ID(400,"Employee ID doesn't exist !! "),
   EMPLOYEE_INVALID_NAME(400,"Employee name doesn't exist !! "),
   EMPLOYEE_INVALID_STATUS(400,"Entered status is invalid !! "),
   EMPLOYEE_INVALID_EMAIL(400,"Employee email doesn't exist !! "),
   EMPLOYEE_EMAIL_ALREADY_USED(400,"Employee email already in use please try another email!! "),
   EMPLOYEE_INVALID_FILE_FORMAT(400,"Employee doesn't exist !! "),
   EMPLOYEE_FILE_DATA_SAVE(201,"Excel file data saved successfully !!! "),
   EMPLOYEE_UPDATED_SUCCESSFULLY(200,"Employee data updated successfully !!!"),
   FAIL_STORE_FILE_DATA(400,"fail to store file data !!! "),
   FAIL_PARSE_FILE_DATA(400,"fail to parse file data !!! "),
    NEW_PASSWORD_CNF_PASSWORD_NOT_MATCH(400,"new password and confirm password do not match please check again !!!" ),
    PLEASE_ENTER_NEW_PASSWORD(400,"please enter the new password you have not enter it !!" ),
    PLEASE_ENTER_CNF_PASSWORD(400,"please enter the confirm password you have not enter it !!" ),
    PLEASE_ENTER_EMAIL(400,"please enter the email you have not enter it !!" ),
    EMPLOYEE_PASSWORD_SUCCESSFULLY_UPDATED(200,"Employee password has been successfully updated !!! " ),
    PLEASE_ENTER_PREVOIUS_PASSWORD(400,"Please enter the previous password !!! " ),
    ENTERED_PREVIOUS_PASSWORD_WRONG(400,"Please enter the correct previous password !!! " ),
    DEPARTMENT_NOT_FOUND(404,"Department doesn't exist !! " ),
    LOG_OUT_SUCCESSFULLY(200,"Logging out successfully ! " ),
    INVALID_DEPARTMENT_ID(404,"department not found with given id !! " ),
    DEPARTMENT_STATUS_ACTIVATED_SUCCESSFULLY(200,"department status activated successfully !!" ),
    DEPARTMENT_STATUS_DEACTIVATED_SUCCESSFULLY(200,"department status deactivated successfully !!" ),
    DEPARTMENT_STATUS_TEMPORARILY_DELETED_SUCCESSFULLY(200, "department status temporarily deleted successfully !!"),
    INVALID_STATUS(400,"invalid status to update" ), ID_ALREADY_EXIST_WITH_GIVEN_DATA(400,"id already exist with given name and location !!" );

    private final int code;
    @Getter
    private final String message;


    ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code(){
        return code;
    }

}
