package com.matrix.numbers.matrix.dto;

import com.matrix.numbers.matrix.validation.ValidDateRange;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MatrixDto {

    @NotBlank(message = "Date cannot be blank")
    @ValidDateRange(message = "Date must be in the format YYYYMMDD and within 200 years from the current year")
    private String date;
}
