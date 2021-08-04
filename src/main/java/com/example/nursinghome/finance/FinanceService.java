package com.example.nursinghome.finance;

import com.example.nursinghome.elder.Elder;
import com.example.nursinghome.elder.ElderNotFoundException;
import com.example.nursinghome.elder.ElderRepository;
import com.example.nursinghome.invoice.CreateInvoiceCommand;
import com.example.nursinghome.invoice.Invoice;
import com.example.nursinghome.invoice.InvoiceRepository;
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
        Elder elder = elderRepository.findById(command.getElderId()).orElseThrow(() -> new ElderNotFoundException("No Elder with this Id"));
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
        Elder elder = elderRepository.findById(id).orElseThrow(() -> new ElderNotFoundException("No elder with this: " + id));
        System.out.println(elder);
        Invoice invoice = new Invoice(command.getAmount(), command.getDescription());
        invoiceRepository.save(invoice);
        Finance finance = elder.getFinance();
        finance.addInvoice(invoice);
        return modelMapper.map(finance, FinanceDTO.class);
    }

    public FinanceDTO getFinanceAccount(long id) {
        Elder elder = elderRepository.findById(id).orElseThrow(() -> new ElderNotFoundException("Cannot find Elder with this: " + id));
        Finance finance = elder.getFinance();
        return modelMapper.map(finance, FinanceDTO.class);
    }
}
