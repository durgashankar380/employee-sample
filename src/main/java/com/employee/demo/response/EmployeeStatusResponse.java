package com.employee.demo.response;

import com.employee.demo.ApiStatus.ApiStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeStatusResponse {

    private ApiStatus message;
}
