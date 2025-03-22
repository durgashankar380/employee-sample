package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtForgetPasswordRequest {

    @NonNull
    private String email;
    @NonNull
    private String newPassword;
    @NonNull
    private String confirmPassword;

}
