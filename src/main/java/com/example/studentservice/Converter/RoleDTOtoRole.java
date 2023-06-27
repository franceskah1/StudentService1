package com.example.studentservice.Converter;
import com.example.studentservice.dto.RoleDTO;
import com.example.studentservice.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleDTOtoRole implements Converter<RoleDTO, Role> {
    @Override
    public Role convert(RoleDTO source) {
        if (source != null) {
            Role role = new Role();
            role.setId(source.getId());
            role.setName(source.getName());
            return role;
        }
        return null;
    }
}