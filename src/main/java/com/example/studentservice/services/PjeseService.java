package com.example.studentservice.services;
import com.example.studentservice.Converter.PjeseDTOtoPjes;
import com.example.studentservice.Converter.PjeseToPjeseeDTO;
import com.example.studentservice.dto.PjeseDTO;
import com.example.studentservice.excetions.NotFoundException;
import com.example.studentservice.repositories.PjeseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PjeseService {
    private final PjeseToPjeseeDTO pjeseToPjeseeDTO;
    private final PjeseDTOtoPjes pjeseDTOtoPjes;
    private final PjeseRepo pjeseRepo;

    //save update
    public PjeseDTO saveOrUpdate(PjeseDTO pjeseDTO){
        return pjeseToPjeseeDTO.convert(pjeseRepo.save(Objects.requireNonNull(pjeseDTOtoPjes.convert(pjeseDTO))));
    }

    //find all
    public List<PjeseDTO> findAll(){
      return   pjeseRepo.findAll().stream().map(pjese -> pjeseToPjeseeDTO.convert(pjese)).collect(Collectors.toList());
    }

    //find pjese by id
    public PjeseDTO findById(Long id){
        return pjeseToPjeseeDTO.convert(pjeseRepo.findById(id).orElseThrow(()->new NotFoundException("Pjese with id:"+id +" not found!")));
    }

    //delete by id
    public void deletePjeseById(String id){
        long parseId;
        try {
            parseId = Long.parseLong(id);
            pjeseRepo.deleteById(parseId);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Pjese id: \"" + id + "\" can't be parsed!");
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException("Pjese with id: " + id + " doesn't exist!");
        }

    }
}
