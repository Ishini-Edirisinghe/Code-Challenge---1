package com.example.aadpos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ItemDTO {
    private String item_id;
    private String item_name;
    private int quantity;
    private double price;
}
