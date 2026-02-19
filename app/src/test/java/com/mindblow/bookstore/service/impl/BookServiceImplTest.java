package com.mindblow.bookstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.mindblow.bookstore.dto.BookDTO;
import com.mindblow.bookstore.dto.BookSaveDTO;
import com.mindblow.bookstore.entity.Author;
import com.mindblow.bookstore.entity.Book;
import com.mindblow.bookstore.entity.Publisher;
import com.mindblow.bookstore.exception.AuthorNotFoundException;
import com.mindblow.bookstore.repository.AuthorRepository;
import com.mindblow.bookstore.repository.BookRepository;
import com.mindblow.bookstore.service.BookService;

@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private BookService service;

    @MockitoBean
    private BookRepository repository;

    @MockitoBean
    private AuthorRepository authorRepository;

    private Book entity;

    private BookSaveDTO saveDTO;
    private Author author1 = new Author();
    private Author author2 = new Author();

    @BeforeEach void setUp(){
        entity = new Book();
        entity.setName("Celular: como dar um tempo");
        entity.setSynopsis("Dicas práticas testadas para realmente mudar a vida...");
        entity.setPrice(16875L);
        entity.setOldPrice(21052L);
        entity.setIssn("12345678");
        entity.setYear(2018L);
        Publisher publisher = new Publisher();
        publisher.setName("Fontanar");
        publisher.setBooks(Set.of(entity)); 

        author1.setName("John");
        author2.setName("Mary");
        author1.setBooks(Set.of(entity));
        author2.setBooks(Set.of(entity));
        entity.setPublisher(publisher);
        entity.setAuthors(Set.of(author1, author2));
    }


    @Test void shouldFindById(){
        given(repository.findById(69L)).willReturn(Optional.of(entity));

        BookDTO dto = assertDoesNotThrow(() -> service.findById(69L).get());

        assertNotNull(dto);
        assertEquals("Celular: como dar um tempo", dto.name());

    }

    @Test void shouldFindByAuthor() throws AuthorNotFoundException{
        given(authorRepository.findById(13L)).willReturn(Optional.of(author1));
        given(authorRepository.findById(12L)).willReturn(Optional.of(author2));
    
        // Test author1
        Set<BookDTO> dto = service.findByAuthor(13L);
        List<BookDTO> dtoList = dto.stream().collect(Collectors.toList());
        BookDTO first = dtoList.getFirst();
        // Not empty
        assertFalse(dto.isEmpty());
        assertEquals(dto.size(), 1);
        assertEquals(first.name(), "Celular: como dar um tempo");

        // Test author2
        dto = service.findByAuthor(12L);
        dtoList = dto.stream().collect(Collectors.toList());
        first = dtoList.getFirst();
        // Not empty
        assertFalse(dto.isEmpty());
        assertEquals(dto.size(), 1);
        assertEquals(first.name(), "Celular: como dar um tempo");
    }
}

