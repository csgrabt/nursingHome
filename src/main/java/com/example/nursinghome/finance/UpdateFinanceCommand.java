package com.example.nursinghome.finance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFinanceCommand {
    @Schema(description = "The Id of the old man", defaultValue = "1")
    @Positive
    private long elderId;
    @Schema(description = "The starting money of the old man", defaultValue = "100000")
    @Positive
    private BigInteger balance;
}
