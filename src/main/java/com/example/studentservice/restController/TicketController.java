package com.example.studentservice.restController;
import com.example.studentservice.dto.TicketDTO;
import com.example.studentservice.model.Ticket;
import com.example.studentservice.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<?>saveTicket(@RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateTicket(@PathVariable("id")Long id, @RequestBody Ticket ticket){
        return ticketService.updateTicket(id,ticket);

    }

    @GetMapping("/{id}")
    public TicketDTO findById(@PathVariable("id")Long id){
        return ticketService.findById(id);
    }

    @GetMapping
    public List<TicketDTO>findAll(){
        return ticketService.findAll();
    }



    @GetMapping("/findTicket/{userId}")
    public List<TicketDTO>  findUserById(@PathVariable("userId")String userId){
        return ticketService.findByStudentId(userId);
    }
}
