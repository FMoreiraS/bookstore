package com.mindblow.bookstore.dto;

public record BookDTO(
    Long id,
    String name,
    String synopsis,
    Long price,
    Long oldPrice,
    Long year,
    String issn
) {
}
