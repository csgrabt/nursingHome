package com.example.nursinghome.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String zipCode;
    private String city;
    private String street;
    private String houseNumber;

    public Address(String zipCode, String city, String street, String houseNumber) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
