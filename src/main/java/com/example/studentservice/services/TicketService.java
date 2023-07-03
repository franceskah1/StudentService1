package com.example.studentservice.services;
import com.example.studentservice.Converter.TicketToTicketDTO;
import com.example.studentservice.dto.TicketDTO;
import com.example.studentservice.excetions.NotFoundException;
import com.example.studentservice.model.Pjese;
import com.example.studentservice.model.Ticket;
import com.example.studentservice.model.TicketStatus;
import com.example.studentservice.model.User;
import com.example.studentservice.repositories.PjeseRepo;
import com.example.studentservice.repositories.TicketRepo;
import com.example.studentservice.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepo ticketRepo;

    private final TicketToTicketDTO ticketToTicketDTO;

    private final PjeseRepo pjeseRepo;

    private final UserRepo userRepo;

   //create ticket
    public ResponseEntity<Ticket> createTicket(Ticket ticket) {
        User user=userRepo.findById(ticket.getUser().getId()).get();
        Pjese pjese = pjeseRepo.findById(ticket.getPjese().getId()).orElse(null);
        if (pjese == null || pjese.getStock() == 0) {
            throw new IllegalArgumentException("Part not found or out of stock");
        }

        pjese.setStock(pjese.getStock() - 1); // Reduce the stock of the associated part
        pjeseRepo.save(pjese);
        ticket.setUser(user);
        ticket.setTicketStatus(TicketStatus.OPEN);

        Ticket savedTicket = ticketRepo.save(ticket);

        return ResponseEntity.ok(savedTicket);
    }
//update ticket
    public ResponseEntity<Ticket> updateTicket( Long id, Ticket ticket) {
        Optional<Ticket> existingTicket = ticketRepo.findById(id);
        if (existingTicket.isPresent()) {
            Ticket updatedTicket = existingTicket.get();
            updatedTicket.setDescription(ticket.getDescription());
            updatedTicket.setTicketStatus(ticket.getTicketStatus());
           ticketRepo.save(updatedTicket);
            return ResponseEntity.ok(updatedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //find All ticket
    public List<TicketDTO> findAll(){
        return   ticketRepo.findAll().stream().map(ticketToTicketDTO::convert).collect(Collectors.toList());
    }

    //find pjese by id
    public TicketDTO findById(Long id){
        return ticketToTicketDTO.convert(ticketRepo.findById(id).orElseThrow(()->new NotFoundException("Ticket with id:"+id +" not found!")));
    }


    //delete by id
    public void deleteTicketById(String id) {
        Long parseId;
        try {
            parseId = Long.parseLong(id);
            ticketRepo.deleteById(parseId);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ticket id: \"" + id + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Ticket with id: " + id + " doesn't exist!");
        }

    }
    public ResponseEntity<Ticket> closeTicket( Long id) {
        Optional<Ticket> existingTicket = ticketRepo.findById(id);
        if (existingTicket.isPresent()) {
            Ticket ticket = existingTicket.get();
            ticket.setTicketStatus(TicketStatus.CLOSED);
            ticketRepo.save(ticket);
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<TicketDTO>findByStudentId(String userId){
        Long parseId;
        try {
            parseId = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ticket id: \"" + userId + "\" can't be parsed!");
        }

        return ticketRepo.findByUserId(parseId).stream().map(ticketToTicketDTO::convert).collect(Collectors.toList());
    }
    }



