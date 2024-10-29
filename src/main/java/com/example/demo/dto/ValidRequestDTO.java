package com.example.demo.dto;

import com.example.demo.validated.Telephone;
import com.example.demo.validated.ValidationGroup;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidRequestDTO {
    @NotBlank
    public String name;

    @Email
    public String email;

    //@Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Telephone
    public String phoneNumber;

    @Min(value = 20)
    @Max(value = 40)
    public int age;

    @Size(min = 0, max = 40)
    public String description;

    @Positive(groups = ValidationGroup.class)
    public int count;

    @AssertTrue
    public boolean booleanCheck;
}
