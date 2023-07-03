package com.example.studentservice.services;

import com.example.studentservice.dto.LoginDTO;
import com.example.studentservice.dto.LoginResponse;
import com.example.studentservice.model.User;
import com.example.studentservice.repositories.UserRepo;
import com.example.studentservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserRepo usersRepository;
    private final AuthenticationProvider authenticationManager;
    private final JwtUtils jwtUtils;

    public Object login(LoginDTO loginDto) {

            try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(), loginDto.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                Optional<User> user = usersRepository.findByEmail(loginDto.getEmail());
                if (user.isPresent()) {
                    LoginResponse loginResponse = new LoginResponse();
                    String token = jwtUtils.generateAccessToken(user.get());
                    loginResponse.setAccessToken(token);
                    loginResponse.setUserData(user.get());
                    return loginResponse;
                }
                throw new RuntimeException("User with email " + loginDto.getEmail() + " not found!");

            } catch (BadCredentialsException badCredentialsException) {
                throw new BadCredentialsException("Email or password is not correct!");
            }

        }


}



