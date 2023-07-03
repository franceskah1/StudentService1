package com.example.studentservice.Converter;

import com.example.studentservice.dto.PjeseDTO;
import com.example.studentservice.model.Pjese;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PjeseDTOtoPjes implements Converter<PjeseDTO, Pjese> {
    @Override
    public Pjese convert(PjeseDTO source) {
     if (source!=null){
         Pjese pjese=new Pjese();
         pjese.setDescription(source.getDescription());
         pjese.setId(source.getId());
         pjese.setName(source.getName());
         pjese.setStock(source.getStock());
         pjese.setPrice(source.getPrice());
         return pjese;
     }
        return null;
    }
}
