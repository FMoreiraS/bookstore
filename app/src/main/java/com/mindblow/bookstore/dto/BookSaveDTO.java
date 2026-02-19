package com.mindblow.bookstore.dto;
import java.util.Set;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record BookSaveDTO(
    @NotBlank String name,
    @NotBlank String synopsis,
    @Min(value = 0) Long price,
    Long oldPrice,
    @NotNull Long year,
    @NotBlank @Size(min = 8, max = 9) String issn,
    @NotEmpty Set<Long> authorIds,
    @NotNull Long publisherId
) {
}
