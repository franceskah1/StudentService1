package com.example.studentservice.Converter;

import com.example.studentservice.dto.RoleDTO;
import com.example.studentservice.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleDTO implements Converter<Role, RoleDTO> {
    @Override
    public RoleDTO convert(Role source) {
        if (source!=null){
            RoleDTO roleDTO=new RoleDTO();
            roleDTO.setId(source.getId());
            roleDTO.setName(source.getName());
            return roleDTO;
        }
        return null;
    }
}
