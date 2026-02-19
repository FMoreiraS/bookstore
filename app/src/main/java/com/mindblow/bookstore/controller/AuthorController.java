package com.mindblow.bookstore.controller;

import java.util.Optional;

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
import com.mindblow.bookstore.dto.AuthorSaveDTO;
import com.mindblow.bookstore.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<AuthorDTO> author = service.findById(id);
        if(author.isEmpty())
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        "Not able to find author with the given id=%d".formatted(id))).build();
        return ResponseEntity.of(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorSaveDTO authorSaveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(authorSaveDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody @Valid AuthorSaveDTO authorSaveDTO, BindingResult result){
        return ResponseEntity.of(service.update(id, authorSaveDTO));
    }
}

