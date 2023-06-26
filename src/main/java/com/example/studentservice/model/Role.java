package com.example.studentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role extends BaseEntity{
    @Column(nullable = false, length = 45)
    private String name;



    public Role(String name) {
        super();
        this.name = name;
    }

    public Role() {

    }


    @Override
    public String toString() {
        return this.name;
    }


}
