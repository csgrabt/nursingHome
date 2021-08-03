package com.example.nursinghome.elder;

import com.example.nursinghome.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElderDTO {
    private Long id;
    private String name;
    private Address address;


    public ElderDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
