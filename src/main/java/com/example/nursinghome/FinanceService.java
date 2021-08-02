package com.example.nursinghome;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
@AllArgsConstructor
public class FinanceService {
    private FinanceRepository financeRepository;
    private InvoiceRepository invoiceRepository;
    private ElderRepository elderRepository;
    private ModelMapper modelMapper;

    @Transactional
    public FinanceDTO createFinance(CreateFinanceCommand command) {
        Elder elder = elderRepository.findById(command.getElderId()).orElseThrow(() -> new IllegalArgumentException("No Elder with this Id"));
        Finance finance = new Finance(command.getBalance());
        finance.connection(elder);
        return modelMapper.map(finance, FinanceDTO.class);

    }
}
