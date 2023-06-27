package com.example.studentservice.services;
import com.example.studentservice.Converter.PjeseDTOtoPjes;
import com.example.studentservice.Converter.PjeseToPjeseeDTO;
import com.example.studentservice.dto.PjeseDTO;
import com.example.studentservice.repositories.PjeseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PjeseService {
    private final PjeseToPjeseeDTO pjeseToPjeseeDTO;
    private final PjeseDTOtoPjes pjeseDTOtoPjes;
    private final PjeseRepo pjeseRepo;

    public PjeseDTO saveOrUpdate(PjeseDTO pjeseDTO){
        return pjeseToPjeseeDTO.convert(pjeseRepo.save(Objects.requireNonNull(pjeseDTOtoPjes.convert(pjeseDTO))));
    }


}
