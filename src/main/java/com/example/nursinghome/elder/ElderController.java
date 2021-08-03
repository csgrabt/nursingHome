package com.example.nursinghome.elder;

import com.example.nursinghome.address.UpdateAddressCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204", description = "Elder has been deleted")
    @Operation(summary = "Delete an Elder based on Id")
    public void deleteElder(@PathVariable("id") Long id) {
        elderService.deleteElderById(id);
    }


    @ExceptionHandler({ElderNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(ElderNotFoundException enf) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("Elder/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(enf.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }
}
