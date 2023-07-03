package com.example.studentservice.restController;
import com.example.studentservice.dto.ChangePassDto;
import com.example.studentservice.dto.UserDTO;
import com.example.studentservice.excetions.DuplicateEmailException;
import com.example.studentservice.excetions.PasswordCheckException;
import com.example.studentservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class

UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> saveUpdate(@RequestBody UserDTO usersDTO) {
        try {
            userService.saveUpdate(usersDTO);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DuplicateEmailException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    //get User By Id
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable String id) {
        return userService.findById(id);
    }

   //get All Users
    @GetMapping
    public List<UserDTO> findAll(@RequestParam(name = "enabled", required = false) Boolean isEnabled) {
        if (isEnabled == null) {
            return userService.findAll();
        } else {
            return userService.findAllByIsEnabled(isEnabled);
        }
    }
// update Password
    @PutMapping(path = "/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody ChangePassDto changePasswordDto) {
        try {
            return new ResponseEntity<>(userService.updatePassword(changePasswordDto), HttpStatus.OK);
        } catch (PasswordCheckException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}





