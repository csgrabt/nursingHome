package com.example.nursinghome.address;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Set an Address to Elder")
public class UpdateAddressCommand {
    @Schema(defaultValue = "5400")
    private String zipCode;
    @Schema(defaultValue = "Xuzhou")
    private String city;
    @Schema(defaultValue = "Kamchatka")
    private String street;
    @Schema(defaultValue = "54")
    private String houseNumber;
}
