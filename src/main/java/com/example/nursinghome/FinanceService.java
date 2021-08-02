package com.example.nursinghome;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class FinanceService {
    private FinanceRepository financeRepository;
    private InvoiceRepository invoiceRepository;
    private ElderRepository elderRepository;
    private ModelMapper modelMapper;


}
