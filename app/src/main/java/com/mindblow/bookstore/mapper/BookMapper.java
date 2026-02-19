package com.mindblow.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.mindblow.bookstore.dto.BookDTO;
import com.mindblow.bookstore.dto.BookSaveDTO;
import com.mindblow.bookstore.entity.Book;

@Component
public class BookMapper {
    public BookDTO toDTO(Book entity){
        BookDTO dto = new BookDTO(entity.getId(), entity.getName(), entity.getSynopsis(), entity.getPrice(), entity.getOldPrice(), entity.getYear(), entity.getIssn());
        return dto;
    }

    public Book toEntity(BookSaveDTO bookSaveDTO){
        Book entity = new Book();
        entity.setName(bookSaveDTO.name());
        entity.setSynopsis(bookSaveDTO.synopsis());
        entity.setPrice(bookSaveDTO.price());
        entity.setOldPrice(bookSaveDTO.price());
        entity.setYear(bookSaveDTO.year());
        entity.setIssn(bookSaveDTO.issn());
        return entity;
    }
}
