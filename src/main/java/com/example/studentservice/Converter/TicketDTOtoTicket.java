package com.example.studentservice.Converter;

import com.example.studentservice.dto.TicketDTO;
import com.example.studentservice.model.Ticket;

import com.example.studentservice.model.TicketStatus;
import com.example.studentservice.utils.TicketStatusUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class TicketDTOtoTicket implements Converter<TicketDTO, Ticket> {
    private final PjeseDTOtoPjes pjeseDTOtoPjes;


    @Override
    public Ticket convert(TicketDTO source) {
  if (source!=null){
      Ticket ticket=new Ticket();
      ticket.setDescription(source.getDescription());
      ticket.setId(source.getId());
      ticket.setLaptopId(source.getLaptopId());
      ticket.setTicketStatus(TicketStatusUtils.getTicket(source.getTicketStatus()));
      ticket.setPjese(pjeseDTOtoPjes.convert(source.getPjeseDTO()));
      return ticket;
  }
        return null;
    }
}
