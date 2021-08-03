package com.example.nursinghome;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigInteger balance;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Invoice> invoices = new ArrayList<>();
    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Elder elder;

    public Finance(BigInteger balance) {
        this.balance = balance;
    }

    public void connection(Elder elder) {
        this.elder = elder;
        elder.setFinance(this);
    }


    public void addInvoice(Invoice invoice) {
        if (invoices == null) {
            invoices = new ArrayList<>();
        }
        invoices.add(invoice);
        this.balance = this.balance.add(invoice.getAmount());
    }
}
