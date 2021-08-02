package com.example.nursinghome;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceCommand {
    @Schema(description = "The amount of the money, can be positive or negative", defaultValue = "-1000")
    private BigInteger amount;
}
