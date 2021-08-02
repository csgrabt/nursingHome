package com.example.nursinghome;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFinanceCommand {
    @Schema(description = "The Id of the old man", defaultValue = "1")
    private long elderId;
    @Schema(description = "The starting money of the old man", defaultValue = "100000")
    private BigInteger balance;
}
