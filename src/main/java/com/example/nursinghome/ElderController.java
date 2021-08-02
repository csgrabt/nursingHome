package com.example.nursinghome;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/elders")
@RestController
@AllArgsConstructor
public class ElderController {
    private ElderService elderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ElderDTO createEmployee(@RequestBody CreateElderCommand command) {
        return elderService.createElder(command);
    }

    @GetMapping
    public List<ElderDTO> listElders(){
        return  elderService.listElders();
    }

}
