package com.example.studentservice.restController;
import com.example.studentservice.dto.LoginDTO;
import com.example.studentservice.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;


    @PostMapping
    public Object authenticateUser(@RequestBody LoginDTO loginDto) {
        Object apiResponse = loginService.login(loginDto);
        return ResponseEntity
                .ok()
                .body(apiResponse);

    }

}




