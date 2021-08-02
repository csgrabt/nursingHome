package com.example.nursinghome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigInteger balance;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Invoice> invoices;
    @OneToOne
    @ToString.Exclude
    private Elder elder;
}
