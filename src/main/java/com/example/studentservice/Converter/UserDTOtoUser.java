package com.example.studentservice.Converter;

import com.example.studentservice.dto.RoleDTO;
import com.example.studentservice.dto.UserDTO;
import com.example.studentservice.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserDTOtoUser implements Converter<UserDTO, User> {
    private final RoleDTOtoRole roleDTOtoRole;
    @SneakyThrows
    @Override
    public User convert(UserDTO source) {
        if (source != null) {
            User user = new User();
            user.setId(source.getId());
            user.setEmail(source.getEmail());
            user.setEnabled(false);
            user.setAccountNonExpired(false);
            if (source.getRoleDTOS() != null)
                user.setRoles(source.getRoleDTOS().stream().map(roleDTOtoRole::convert).collect(Collectors.toSet()));
            return user;
        }
        return null;
    }

}
