package com.example.studentservice.dto;

import com.example.studentservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String accessToken;
    private String email;
    private Long id;
    private List<String> roles;
    private String name;
    private String username;

    public void setUserData(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(role -> role.getName()).toList();
        this.name = user.getName();
        this.username=user.getUsername();




}
}
