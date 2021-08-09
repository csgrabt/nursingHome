package com.example.nursinghome.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long Id;
    private String zipCode;
    private String city;
    private String street;
    private String houseNumber;
}
