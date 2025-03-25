package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResetRequest {
    private String email;
    private String prevPassword;
    private String newPassword;
    private String cnfPassword;
}
