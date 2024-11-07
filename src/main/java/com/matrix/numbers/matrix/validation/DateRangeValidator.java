package com.matrix.numbers.matrix.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, String> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final int YEAR_RANGE = 200;

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null || !date.matches("\\d{4}\\d{2}\\d{2}")) {
            return false;
        }

        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMAT);
            int currentYear = LocalDate.now().getYear();
            int dateYear = parsedDate.getYear();

            return Math.abs(currentYear - dateYear) <= YEAR_RANGE;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
