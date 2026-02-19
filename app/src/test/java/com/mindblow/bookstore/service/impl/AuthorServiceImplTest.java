package com.mindblow.bookstore.service.impl;

import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.AuthorSaveDTO;
import com.mindblow.bookstore.entity.Author;
import com.mindblow.bookstore.repository.AuthorRepository;
import com.mindblow.bookstore.service.AuthorService;

@SpringBootTest
public class AuthorServiceImplTest {
    
    @Autowired
    private AuthorService service;

    @MockitoBean
    private AuthorRepository repository;

    @Test void shouldSaveAuthor(){
        // Arrange
        // Saving anything should return itself.
        given(repository.save(any())).willAnswer((answer) -> answer.getArguments()[0]);
        AuthorSaveDTO saveDTO = new AuthorSaveDTO("Anitta");
        // Act
        AuthorDTO author = service.save(saveDTO);
        // Assert
        assertNotNull(author);
        assertEquals("Anitta", author.name());
    }
    
    @Test void shouldFindAnAuthorByID(){
        Author author = new Author();
        author.setName("Anitta");
        given(repository.findById(69L)).willReturn(Optional.of(author));

        AuthorDTO authorDTO = assertDoesNotThrow(() -> service.findById(69L).get());

        assertEquals("Anitta", authorDTO.name());
    }

    @Test void shouldUpdateAnAuthor(){
        Author author = new Author();
        author.setName("John");
        given(repository.findById(5L)).willReturn(Optional.of(author));;
        given(repository.save(any())).willAnswer(ans -> ans.getArgument(0));

        AuthorDTO authorDTO = assertDoesNotThrow(() -> service.update(5L, new AuthorSaveDTO("Johnatan")).get());

        assertEquals("Johnatan", authorDTO.name());
    }
}
