package com.example.studentservice.restController;
import com.example.studentservice.dto.PjeseDTO;
import com.example.studentservice.dto.UserDTO;
import com.example.studentservice.services.PjeseService;
import com.example.studentservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pjese")
public class PjeseController {
    private final PjeseService pjeseService;
    private final UserService userService;
    @PostMapping
    public void saveOrUpdate(@RequestBody PjeseDTO pjeseDTO){
      pjeseService.saveOrUpdate(pjeseDTO);
    }
    @PutMapping
    public ResponseEntity<?> userRegister(@RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

}
