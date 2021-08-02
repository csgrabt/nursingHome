package com.example.nursinghome;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/elders")
@RestController
@AllArgsConstructor
@Tag(name = "Operations on Elders")
public class ElderController {
    private ElderService elderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates an Aged man")
    @ApiResponse(responseCode = "201", description = "Elder has been created")
    public ElderDTO createEmployee(@RequestBody CreateElderCommand command) {
        return elderService.createElder(command);
    }

    @GetMapping
    @Operation(summary = "Gives back the list of elderly people")
    public List<ElderDTO> listElders() {
        return elderService.listElders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gives back one elderly people based on id")
    public ElderDTO findElderById(@PathVariable("id") long id) {
        return elderService.findElderById(id);
    }

    @PutMapping("/{id}/address")
    @Operation(summary = "Set or update the address of the aged man")
    public ElderDTO setAddress(@PathVariable("id") long id, @RequestBody UpdateAddressCommand command) {
        return elderService.updateAddress(id, command);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete an Elder based on Id")
    public void deleteElder(@PathVariable("id") Long id) {
        elderService.deleteElderById(id);
    }
}
