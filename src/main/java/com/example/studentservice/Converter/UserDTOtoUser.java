package com.example.studentservice.Converter;
import com.example.studentservice.dto.UserDTO;
import com.example.studentservice.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserDTOtoUser implements Converter<UserDTO, User> {
    private final RoleDTOtoRole roleDTOtoRole;
    private final PasswordEncoder passwordEncoder;
    @SneakyThrows
    @Override
    public User convert(UserDTO source) {
        if (source != null) {
            User user = new User();
            user.setId(source.getId());
            user.setUsername(source.getUserName());
            user.setName(source.getName());
            user.setAddress(source.getAddress());
            user.setPhoneNumber(source.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(source.getPassword()));
            user.setEmail(source.getEmail());
            user.setEnabled(false);
            user.setAccountNonExpired(false);
            if (source.getRole() != null)
                user.setRoles(source.getRole().stream().map(roleDTOtoRole::convert).collect(Collectors.toSet()));
            return user;
        }
        return null;
    }

}
