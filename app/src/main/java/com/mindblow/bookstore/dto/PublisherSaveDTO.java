package com.mindblow.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public record PublisherSaveDTO(@NotBlank String name) {}
