package com.example.studentservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pjese extends BaseEntity{

    private String name;

    private String description;

    private double price;

    private int stock;

    @OneToMany(mappedBy="pjese",cascade= CascadeType.ALL)
    private Set<Ticket> tickets=new HashSet<>();
}

