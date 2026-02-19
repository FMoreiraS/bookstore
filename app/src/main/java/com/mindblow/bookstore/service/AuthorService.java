package com.mindblow.bookstore.service;

import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.AuthorSaveDTO;

import java.util.Optional;

public interface AuthorService {
    public Optional<AuthorDTO> findById(Long id);
    public AuthorDTO save(AuthorSaveDTO authorSaveDTO);
    public Optional<AuthorDTO> update(Long id, AuthorSaveDTO authorSaveDTO);
}
