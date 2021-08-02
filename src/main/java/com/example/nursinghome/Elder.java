package com.example.nursinghome;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Elder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    @OneToOne
    private Address address;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Finance finance;



    public Elder(String name, LocalDate age) {
        this.name = name;
        this.dateOfBirth = age;
    }
}
