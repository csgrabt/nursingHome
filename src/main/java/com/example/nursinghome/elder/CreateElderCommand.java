package com.example.nursinghome.elder;

import com.example.nursinghome.validation.Name;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Create a new Aged man")
public class CreateElderCommand {
    @Schema(description = "Name of the Old aged man", example = "John Doe")
    @Name()
    private String name;
    @Schema(description = "Age of the Elder", example = "2000-10-10")
    @Past(message = "The birthday cannot be earlier than today")
    private LocalDate dateOfBirth;
}
