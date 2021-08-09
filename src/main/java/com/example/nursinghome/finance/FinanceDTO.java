package com.example.nursinghome.finance;

import com.example.nursinghome.invoice.Invoice;
import com.example.nursinghome.invoice.InvoiceDTO;
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
    private List<InvoiceDTO> invoices;

}
