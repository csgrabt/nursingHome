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
    public FinanceDTO createFinance(UpdateFinanceCommand command) {
        Elder elder = elderRepository.findById(command.getElderId()).orElseThrow(() -> new IllegalArgumentException("No Elder with this Id"));
        if (elder.getFinance() != null) {
            throw new IllegalArgumentException("This elder has an account now!");
        }
        Finance finance = new Finance(command.getBalance());
        finance.connection(elder);
        financeRepository.save(finance);
        return modelMapper.map(finance, FinanceDTO.class);

    }

    @Transactional
    public FinanceDTO addInvoice(CreateInvoiceCommand command, Long id) {
        Finance finance = financeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No finance with this id"));
        Invoice invoice = new Invoice(command.getAmount());
        invoiceRepository.save(invoice);
        finance.addInvoice(invoice);
        return modelMapper.map(finance, FinanceDTO.class);
    }

    public FinanceDTO getFinanceAccount(long id) {
        Elder elder = elderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find Elder with this: " + id));
        Finance finance = elder.getFinance();
        return modelMapper.map(finance, FinanceDTO.class);
    }
}
