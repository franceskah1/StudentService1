package com.example.studentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Ticket extends BaseEntity {

    private Long laptopId;

    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    @JoinColumn(name = "pjese_id", referencedColumnName = "id")
    private Pjese pjese;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
