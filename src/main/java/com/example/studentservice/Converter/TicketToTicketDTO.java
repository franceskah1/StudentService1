package com.example.studentservice.Converter;

import com.example.studentservice.dto.TicketDTO;
import com.example.studentservice.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class TicketToTicketDTO implements Converter<Ticket, TicketDTO> {
    private final PjeseToPjeseeDTO toPjeseeDTO;
    @Override
    public TicketDTO convert(Ticket source) {
    if(source!=null){
        TicketDTO ticketDTO=new TicketDTO();
        ticketDTO.setId(source.getId());
        ticketDTO.setDescription(source.getDescription());
        ticketDTO.setLaptopId(source.getLaptopId());
        ticketDTO.setPjeseDTO(toPjeseeDTO.convert(source.getPjese()));
        return ticketDTO;
    }
        return null;
    }
}
