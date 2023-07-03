package com.example.studentservice.restController;
import com.example.studentservice.dto.PjeseDTO;
import com.example.studentservice.services.PjeseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RequiredArgsConstructor
@RestController
@RequestMapping("/pjese")
public class PjeseController {
    private final PjeseService pjeseService;

    @PostMapping
    public ResponseEntity<?> saveOrUpdate(@RequestBody PjeseDTO pjeseDTO) {
        try {
            pjeseService.saveOrUpdate(pjeseDTO);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public PjeseDTO getById(@PathVariable("id") Long id) {
        return pjeseService.findById(id);
    }

    @GetMapping
    public List<PjeseDTO> findAll() {
        return pjeseService.findAll();
    }

}



