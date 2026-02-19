package com.mindblow.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.AuthorSaveDTO;
import com.mindblow.bookstore.entity.Author;

import lombok.NonNull;

@Component
public class AuthorMapper {
    public Author toEntity(@NonNull AuthorSaveDTO authorSaveDTO){
        Author entity = new Author();
        entity.setName(authorSaveDTO.name());
        return entity;
    }

    public AuthorDTO toDTO(@NonNull Author author){
        return new AuthorDTO(author.getId(), author.getName());
    }
}

