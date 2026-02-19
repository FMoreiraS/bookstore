package com.mindblow.bookstore.controller;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.BookDTO;
import com.mindblow.bookstore.dto.BookSaveDTO;
import com.mindblow.bookstore.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<BookDTO> book = service.findById(id);
        if(book.isEmpty())
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        "Not able to find book with the given id=%d".formatted(id))).build();
        return ResponseEntity.ok(service.findById(id).get());
    }

    @GetMapping("/{id}/authors")
    public ResponseEntity<?> findAuthorBooks(@PathVariable Long id){
        Set<AuthorDTO> authorDTOs = service.findBookAuthors(id);

        return ResponseEntity.ok(authorDTOs);
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookSaveDTO saveDTO, BindingResult result){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(saveDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody @Valid BookSaveDTO saveDTO){
        return ResponseEntity.of(service.updateBook(id, saveDTO));
    }
}

