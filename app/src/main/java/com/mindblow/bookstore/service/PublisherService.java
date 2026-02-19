package com.mindblow.bookstore.service;

import java.util.Optional;

import com.mindblow.bookstore.dto.PublisherDTO;
import com.mindblow.bookstore.dto.PublisherSaveDTO;

public interface PublisherService {
    public Optional<PublisherDTO> findById(Long id);
    public PublisherDTO save(PublisherSaveDTO publisherSaveDTO);
    public Optional<PublisherDTO> update(Long id, PublisherSaveDTO saveDTO);
}
