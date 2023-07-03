package com.example.studentservice.dto;

import lombok.Data;

@Data
public class ChangePassDto {
    private Long id;
    private final String password;
    private final String newPassword;

}
