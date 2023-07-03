package com.example.studentservice.restController;
import com.example.studentservice.services.PjeseService;
import com.example.studentservice.services.TicketService;
import com.example.studentservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final PjeseService pjeseService;
    private final TicketService ticketService;

   private final UserService userService;

    @DeleteMapping("/deleteTicket/{id}")
    public void deleteById(@PathVariable("id")String id){
       ticketService.deleteTicketById(id);

    }


    @DeleteMapping("/deletePjese/{id}")
    public void deletePjese(@PathVariable("id")String id){
        pjeseService.deletePjeseById(id);
    }



    @PutMapping("/{id}/close")
    public ResponseEntity<?> closeTicket(@PathVariable Long id) {

          ticketService.closeTicket(id);

          return new ResponseEntity<>("Ticket status Closed.", HttpStatus.OK);

    }

   @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable("id")String id){
        userService.deleteUserById(id);
   }
}


