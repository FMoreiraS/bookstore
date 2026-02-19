package com.mindblow.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorSaveDTO(@NotBlank String name) {}

