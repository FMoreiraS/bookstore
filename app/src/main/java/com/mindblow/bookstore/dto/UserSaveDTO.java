package com.mindblow.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSaveDTO(
    @NotBlank String fullName,
    @Email String email,
    @Size(min = 10, max = 255) String password
) {
}
