package com.example.nursinghome;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/finances")
@RestController
@AllArgsConstructor
@Tag(name = "Operations on Finances")
public class FinanceController {
    private FinanceService financeService;


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Update an Finance of Aged man")
    public FinanceDTO createFinance(@RequestBody CreateFinanceCommand command) {
        return financeService.createFinance(command);
    }

    @PostMapping("/{id}/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Update an invoice of Aged man, calculate the new Balance")
    public FinanceDTO addInvoice(@PathVariable("id") long id, @RequestBody CreateInvoiceCommand command) {
        return financeService.addInvoice(command, id);
    }
}
