package com.mindblow.bookstore.dto;

import java.util.Set;


public record CartDTO(Long userId, Set<CartItemDTO> items) {
}
