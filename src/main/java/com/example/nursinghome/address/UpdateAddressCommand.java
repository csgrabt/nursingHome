package com.example.nursinghome.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Set an Address to Elder")
public class UpdateAddressCommand {
    @Size(min = 4, max = 4)
    @Schema(defaultValue = "5400")
    private String zipCode;
    @Schema(defaultValue = "Xuzhou")
    @Size(min = 3, max = 20)
    private String city;
    @Schema(defaultValue = "Kamchatka")
    @Size(min = 3, max = 20)
    private String street;
    @Size(min = 1, max = 4)
    @Schema(defaultValue = "54")
    private String houseNumber;
}
