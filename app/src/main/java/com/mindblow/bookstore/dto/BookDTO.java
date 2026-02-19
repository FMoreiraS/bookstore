package com.mindblow.bookstore.dto;

import java.util.Set;

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
