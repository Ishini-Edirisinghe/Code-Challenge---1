package com.example.aadpos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class CustomerDTO {
    private String cus_id;
    private String name;
    private String address;
    private double salary;
}
