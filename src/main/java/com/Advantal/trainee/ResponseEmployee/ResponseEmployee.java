package com.Advantal.trainee.ResponseEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployee {
    private Long id;
    private String name;
    private String department;
    private Double salary;
}