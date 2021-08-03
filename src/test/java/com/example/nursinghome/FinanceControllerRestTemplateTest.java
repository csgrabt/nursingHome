package com.example.nursinghome;

import com.example.nursinghome.elder.CreateElderCommand;
import com.example.nursinghome.elder.ElderDTO;
import com.example.nursinghome.elder.ElderService;
import com.example.nursinghome.finance.FinanceDTO;
import com.example.nursinghome.finance.FinanceService;
import com.example.nursinghome.finance.UpdateFinanceCommand;
import com.example.nursinghome.invoice.CreateInvoiceCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinanceControllerRestTemplateTest {
    @Autowired
    TestRestTemplate template;
    @Autowired
    ElderService elderService;
    @Autowired
    FinanceService financeService;

    ElderDTO elderDTO;

    @BeforeEach
    void deleteAll() {
        elderService.deleteAll();


    }

    @Test
    void testFinanceSet() {
        elderDTO = template.postForObject("/api/elders",
                new CreateElderCommand("John Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);

        template.put("/api/finances", new UpdateFinanceCommand(elderDTO.getId(), new BigInteger("55555")), ElderDTO.class);
        FinanceDTO financeDTO = template.getForObject("/api/finances/elder/" + elderDTO.getId(), FinanceDTO.class);
        assertEquals(new BigInteger("55555"), financeDTO.getBalance());
        System.out.println(financeDTO);
    }

    @Test
    void testInvoiceAdd() {
        elderDTO = template.postForObject("/api/elders",
                new CreateElderCommand("John Doe", LocalDate.of(2000, 10, 1)), ElderDTO.class);

        template.put("/api/finances", new UpdateFinanceCommand(elderDTO.getId(), new BigInteger("55555")), ElderDTO.class);

        template.postForObject("/api/finances/elder/" + elderDTO.getId() + "/invoice", new CreateInvoiceCommand(new BigInteger("-5555")), FinanceDTO.class);

        FinanceDTO financeDTO = template.getForObject("/api/finances/elder/" + elderDTO.getId(), FinanceDTO.class);


        assertEquals(new BigInteger("50000"), financeDTO.getBalance());
    }
}