package com.example.studentservice.repositories;

import com.example.studentservice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findByUserId(Long userId);

}
