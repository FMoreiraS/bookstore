package com.mindblow.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.mindblow.bookstore.repository.AuthorRepository;
import com.mindblow.bookstore.service.AuthorService;


import com.mindblow.bookstore.entity.Author;
import com.mindblow.bookstore.mapper.AuthorMapper;
import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.AuthorSaveDTO;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorMapper mapper;

    @Autowired
    private AuthorRepository repository;
    
    @Override
    public Optional<AuthorDTO> findById(Long id){
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public AuthorDTO save(AuthorSaveDTO authorSaveDTO){
        Author entity = repository.save(mapper.toEntity(authorSaveDTO));
        return mapper.toDTO(entity);
    }

    @Override
    public Optional<AuthorDTO> update(Long id, AuthorSaveDTO authorSaveDTO){
        Optional<Author> author = repository.findById(id); 
        if(author.isEmpty()) return Optional.empty();
        Author entity = author.get();
        entity.setName(authorSaveDTO.name());
        return Optional.of(mapper.toDTO(repository.save(entity)));
    }

}

