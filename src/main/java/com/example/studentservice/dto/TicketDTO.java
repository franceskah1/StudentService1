package com.example.studentservice.dto;
import lombok.Data;

@Data
public class TicketDTO {

    private Long id;

    private Long laptopId;

    private String description;

    PjeseDTO pjeseDTO;
}
