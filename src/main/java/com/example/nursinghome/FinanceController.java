package com.example.nursinghome;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/finances")
@RestController
@AllArgsConstructor
public class FinanceController {
    private FinanceService financeService;
    private ElderService elderService;
}
