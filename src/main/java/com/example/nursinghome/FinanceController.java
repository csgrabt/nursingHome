package com.example.nursinghome;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;

@RequestMapping("/api/finances")
@RestController
@AllArgsConstructor
@Tag(name = "Operations on Finances")
public class FinanceController {
    private FinanceService financeService;

    @GetMapping("/elder/{id}")
    @Operation(summary = "Get back an finance based on elder Id")
    public FinanceDTO findAccountBasedOnElderId(@PathVariable("id") long id) {
        return financeService.getFinanceAccount(id);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Update an Finance of Aged man")
    public FinanceDTO createFinance(@RequestBody UpdateFinanceCommand command) {
        return financeService.createFinance(command);
    }

    @PostMapping("/elder/{id}/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Update an invoice of Aged man, calculate the new Balance")
    public FinanceDTO addInvoice(@PathVariable("id") long id, @RequestBody CreateInvoiceCommand command) {
        return financeService.addInvoice(command, id);
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
