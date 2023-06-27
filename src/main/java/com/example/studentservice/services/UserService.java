package com.example.studentservice.services;

import com.example.studentservice.Converter.UserDTOtoUser;
import com.example.studentservice.Converter.UserToUserDTO;
import com.example.studentservice.dto.UserDTO;
import com.example.studentservice.model.User;
import com.example.studentservice.repositories.RoleRepo;
import com.example.studentservice.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 60;
    private final UserToUserDTO userToUserDTO;
    private final UserDTOtoUser userDTOtoUser;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

public ResponseEntity<?>registerUser(UserDTO userDTO){
    User userToSave=new User();
    if(userRepo.existsByEmail(userDTO.getEmail())==Boolean.TRUE)
    {
        return new ResponseEntity<>("Email is already taken!", HttpStatus.CONFLICT);
    }
    if(userRepo.existsByUsername(userDTO.getUserName())==Boolean.TRUE)
    {  //throw new IllegalStateException("username already taken");
        return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);

    }else {
        userToSave.setUsername(userDTO.getUserName());
        userToSave.setName(userDTO.getName());
        userToSave.setEmail(userDTO.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userDTO.getPassword());
        userToSave.setPassword(encodePassword);
       userToSave.setRoles(Set.of(roleRepo.findById(userDTO.getRoleDTOS().stream().collect(Collectors.toList()).get(0).getId()).orElseThrow()));
       userRepo.save(userToSave);
    }
    return new ResponseEntity<>("User saved success!",HttpStatus.OK);
}
}
