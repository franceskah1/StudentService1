package com.example.studentservice.Converter;

import com.example.studentservice.dto.UserDTO;
import com.example.studentservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserToUserDTO implements Converter<User, UserDTO> {
    private final RoleToRoleDTO roleToRoleDTO;

    @Override
    public UserDTO convert(User source) {
        if (source!=null){
            UserDTO userDTO=new UserDTO();
            userDTO.setId(source.getId());
            userDTO.setUserName(source.getUsername());
            userDTO.setName(source.getName());
            userDTO.setEmail(source.getEmail());
            userDTO.setRoleDTOS((source.getRoles() != null ? source.getRoles().stream().map(role -> roleToRoleDTO.convert(role)).collect(Collectors.toSet()) : null));
            userDTO.setEnabled(source.isEnabled());
            return userDTO;
        }
        return null;
    }
}
