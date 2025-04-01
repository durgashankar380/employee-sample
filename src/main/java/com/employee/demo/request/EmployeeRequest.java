package com.employee.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest  {
    private long employeeId;
    private String name;
   // private String department;
    private Double salary;
    private Integer status;
//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
    private String email;

    private String password;
}
