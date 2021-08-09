package com.example.nursinghome.elder;

import com.example.nursinghome.address.Address;import com.example.nursinghome.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElderDTO {
    private Long id;
    private String name;
    private AddressDTO address;


    public ElderDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
