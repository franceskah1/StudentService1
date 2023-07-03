package com.example.studentservice.utils;

import com.example.studentservice.excetions.NotFoundException;
import com.example.studentservice.model.TicketStatus;

public class TicketStatusUtils {
    public static TicketStatus getTicket(String ticketStatus) {
        if (ticketStatus.equalsIgnoreCase(TicketStatus.OPEN.name())) {
            return TicketStatus.OPEN;

        } else if
        (ticketStatus.equalsIgnoreCase(TicketStatus.CLOSED.name())) {
            return TicketStatus.CLOSED;
        } else {
            throw new NotFoundException("This TicketStatus is not valid. Select a status from list!\"");
        }
    }
}