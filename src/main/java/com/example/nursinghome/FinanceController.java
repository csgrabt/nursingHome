package com.example.nursinghome;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/finances")
@RestController
@AllArgsConstructor
public class FinanceController {
    private FinanceService financeService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FinanceDTO createFinance(@RequestBody CreateFinanceCommand command){
        return financeService.createFinance(command);
    }

}
