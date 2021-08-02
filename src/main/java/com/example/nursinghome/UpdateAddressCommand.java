package com.example.nursinghome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressCommand {

    private String zipCode;
    private String city;
    private String street;
    private String houseNumber;
}
