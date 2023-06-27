package com.example.studentservice.restController;
import com.example.studentservice.dto.PjeseDTO;
import com.example.studentservice.services.PjeseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pjese")
public class PjeseController {
    private final PjeseService pjeseService;
    @PostMapping
    public void saveOrUpdate(@RequestBody PjeseDTO pjeseDTO){
      pjeseService.saveOrUpdate(pjeseDTO);
    }

}
