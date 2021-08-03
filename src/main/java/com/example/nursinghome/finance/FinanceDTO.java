package com.example.nursinghome.finance;

import com.example.nursinghome.invoice.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceDTO {
    private Long id;
    private BigInteger balance;
    private List<Invoice> invoices;

}
