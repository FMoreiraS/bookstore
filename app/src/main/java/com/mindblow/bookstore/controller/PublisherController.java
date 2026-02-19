package com.mindblow.bookstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindblow.bookstore.dto.PublisherDTO;
import com.mindblow.bookstore.dto.PublisherSaveDTO;
import com.mindblow.bookstore.service.PublisherService;

@RestController
@RequestMapping(value = "/publishers")
public class PublisherController {

    @Autowired
    private PublisherService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<PublisherDTO> dto = service.findById(id);
        if(dto.isEmpty())
            return ResponseEntity.of(
                    ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        "Not able to find publisher with id=%d".formatted(id))).build();
        return ResponseEntity.of(dto);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PublisherSaveDTO publisherSaveDTO){
        PublisherDTO dto = service.save(publisherSaveDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
