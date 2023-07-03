package com.example.studentservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String userName;
    private String password;
    private String phoneNumber;
    private String address;
    private Set<RoleDTO>role;
    private boolean enabled;

}
