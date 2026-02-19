package com.mindblow.bookstore.service;

import java.util.Optional;
import java.util.Set;

import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.BookDTO;
import com.mindblow.bookstore.dto.BookSaveDTO;

public interface BookService {
    public Optional<BookDTO> findById(Long id);
    public BookDTO save(BookSaveDTO bookSaveDTO);
    public Set<BookDTO> findByAuthor(Long authorId);
    public Set<AuthorDTO> findBookAuthors(Long bookId);
    public Optional<BookDTO> updateBook(Long bookId, BookSaveDTO bookSaveDTO);
}

