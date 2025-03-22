package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResetPasswordRequest {

    @NonNull
    private String email;
    @NonNull
    private String previousPassword;
   @NonNull
    private String newPassword;
   @NonNull
   private String confirmPassword;



}
