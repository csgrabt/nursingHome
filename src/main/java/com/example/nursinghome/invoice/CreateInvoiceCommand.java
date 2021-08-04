package com.example.nursinghome.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceCommand {
    @Schema(description = "The amount of the money, can be positive or negative", defaultValue = "-1000")
    @NotNull
    private BigInteger amount;
    @Schema(description = "The reason of the invoice", defaultValue = "Buying condoms")
    private String description;
}
