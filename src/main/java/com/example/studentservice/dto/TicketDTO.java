package com.example.studentservice.dto;
import lombok.Data;

@Data
public class TicketDTO {

    private Long id;

    private Long laptopId;

    private String description;

    private String ticketStatus;

    PjeseDTO pjeseDTO;
}
