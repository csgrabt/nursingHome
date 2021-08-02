package com.example.nursinghome;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/finances")
@RestController
@AllArgsConstructor
public class FinanceController {
    private FinanceService financeService;


    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FinanceDTO createFinance(@RequestBody CreateFinanceCommand command) {
        return financeService.createFinance(command);
    }

    @PostMapping("/{id}/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public FinanceDTO addInvoice(@PathVariable("id") long id, @RequestBody CreateInvoiceCommand command) {
        return financeService.addInvoice(command, id);
    }
}
