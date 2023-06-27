package com.example.studentservice.dto;

import lombok.Data;

@Data
public class PjeseDTO {

    private Long id;

    private String name;

    private String description;

    private double price;

    private int stock;
}
