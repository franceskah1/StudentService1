package com.example.studentservice.repositories;

import com.example.studentservice.model.Pjese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PjeseRepo extends JpaRepository<Pjese,Long> {
}
