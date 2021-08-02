package com.example.nursinghome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateElderCommand {
   // @NotBlank
    private String name;
   // @NotBlank
    private LocalDate dateOfBirth;
}
