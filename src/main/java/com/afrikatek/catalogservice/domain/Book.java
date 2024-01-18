package com.afrikatek.catalogueservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Book(
        @NotBlank(message = "The book ISBN must be defined.")
        @Pattern(
            regexp = "^([0-9]{10}|[0-9]{13})$",
            message = "The ISDN format must be valid."
        )
        String isbn,
        @NotBlank(
            message = "The book title must be defined."
        )
        String title,
        @NotBlank(message = "The book author must be defined.")
        String author,
        Double price
) {
}
