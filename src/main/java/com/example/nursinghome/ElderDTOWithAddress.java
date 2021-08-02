package com.example.nursinghome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElderDTOWithAddress {
    private long id;
    private String name;
    private Address address;
}
