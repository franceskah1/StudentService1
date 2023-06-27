package com.example.studentservice.Converter;

import com.example.studentservice.dto.PjeseDTO;
import com.example.studentservice.model.Pjese;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PjeseToPjeseeDTO implements Converter<Pjese, PjeseDTO> {


    @Override
    public PjeseDTO convert(Pjese source) {
   PjeseDTO pjeseDTO=new PjeseDTO();
   pjeseDTO.setDescription(source.getDescription());
   pjeseDTO.setId(source.getId());
   pjeseDTO.setName(source.getName());
   pjeseDTO.setStock(source.getStock());
   pjeseDTO.setPrice(source.getPrice());
   return pjeseDTO;


    }
}
