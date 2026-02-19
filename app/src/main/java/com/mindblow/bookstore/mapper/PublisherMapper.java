package com.mindblow.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.mindblow.bookstore.dto.PublisherDTO;
import com.mindblow.bookstore.dto.PublisherSaveDTO;
import com.mindblow.bookstore.entity.Publisher;

@Component
public class PublisherMapper {
    public Publisher toEntity(PublisherSaveDTO publisherSaveDTO){
        Publisher entity = new Publisher();
        entity.setName(publisherSaveDTO.name());
        return entity;
    }

    public PublisherDTO toDTO(Publisher publisher){
        PublisherDTO dto = new PublisherDTO(publisher.getId(), publisher.getName());
        return dto;
    }
}
