package com.example.studentservice.services;
import com.example.studentservice.Converter.UserDTOtoUser;
import com.example.studentservice.Converter.UserToUserDTO;
import com.example.studentservice.dto.ChangePassDto;
import com.example.studentservice.dto.UserDTO;
import com.example.studentservice.excetions.DuplicateUsernameException;
import com.example.studentservice.excetions.NotFoundException;
import com.example.studentservice.excetions.PasswordCheckException;
import com.example.studentservice.helpers.RegisterHelper;
import com.example.studentservice.model.User;
import com.example.studentservice.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserToUserDTO userToUserDTO;

    private final UserDTOtoUser userDTOtoUser;
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;


    public void saveUpdate(UserDTO userDto) {
        RegisterHelper registerHelper = new RegisterHelper();

        if (userRepo.existsByUsername(userDto.getUserName())) {
            throw new DuplicateUsernameException("userName already exist! Try another.");
        } else {
            userToUserDTO.convert(userRepo.save(Objects.requireNonNull(userDTOtoUser.convert(userDto))));

            registerHelper.getMap();
        }
    }

    public UserDTO findById(String id) {
        long parseId;
        try {
            parseId = Long.parseLong(id);
        } catch (NumberFormatException e) {

            throw new NumberFormatException("Id: " + id + " cannot be parsed");
        }
        return userToUserDTO.convert(userRepo.findById(parseId).orElseThrow(() ->
                new NotFoundException("Record with id: " + id + " not found!")));
    }


    public List<UserDTO> findAll() {
        return userRepo.findAll().stream().map(userToUserDTO::convert).collect(Collectors.toList());
    }

    public List<UserDTO> findAllByIsEnabled(Boolean isEnabled) {
        return userRepo.findAllByIsEnabled(isEnabled).stream().map(user -> userToUserDTO.convert((User) user)).collect(Collectors.toList());

    }

    public void deleteUserById(String id) {
        Long parseId;
        try {
            parseId = Long.parseLong(id);
            userRepo.deleteById(parseId);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("User id: \"" + id + "\" can't be parsed!");
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User with id: " + id + " doesn't exist!");
        }

    }

    public String updatePassword(ChangePassDto changePasswordDTO) throws PasswordCheckException {
        Optional<User> optionalUser = userRepo.findById(changePasswordDTO.getId());
        String result = null;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (changePasswordDTO.getPassword() != null && !changePasswordDTO.getPassword().isEmpty()) {
                if (passwordEncoder.matches(changePasswordDTO.getNewPassword(), user.getPassword())) {
                    throw new RuntimeException("New password cannot be the same as old password!");
                } else if (passwordEncoder.matches(changePasswordDTO.getPassword(), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
                    result = "Updated successfully!";
                } else {
                    throw new PasswordCheckException("Old password is not correct!");
                }
            }
        } else {
            throw new NoSuchElementException("User with id " + changePasswordDTO.getId() + " doesn't exist!");
        }
        return result;
    }
}