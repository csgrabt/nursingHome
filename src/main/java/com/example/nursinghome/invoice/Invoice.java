package com.example.nursinghome.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigInteger;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private BigInteger amount;
    @NotBlank
    private String description;


    public Invoice(BigInteger amount) {
        this.amount = amount;
    }

    public Invoice(BigInteger amount, String description) {
        this.amount = amount;
        this.description = description;
    }
}
