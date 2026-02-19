package com.mindblow.bookstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblow.bookstore.dto.PublisherDTO;
import com.mindblow.bookstore.dto.PublisherSaveDTO;
import com.mindblow.bookstore.entity.Publisher;
import com.mindblow.bookstore.mapper.PublisherMapper;
import com.mindblow.bookstore.repository.PublisherRepository;
import com.mindblow.bookstore.service.PublisherService;


@Service
public class PublisherServiceImpl implements PublisherService{
    @Autowired
    private PublisherMapper mapper;
    @Autowired
    private PublisherRepository repository;
    
    @Override
    public Optional<PublisherDTO> findById(Long id){
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public PublisherDTO save(PublisherSaveDTO publisherSaveDTO){
        return mapper.toDTO(repository.save(mapper.toEntity(publisherSaveDTO)));
    }

    @Override
    public Optional<PublisherDTO> update(Long id, PublisherSaveDTO saveDTO){
        Optional<Publisher> dto = repository.findById(id);
        if(dto.isEmpty()) return Optional.empty();
        Publisher pub = dto.get();
        pub.setName(saveDTO.name());
        pub = repository.save(pub);
        return Optional.of(mapper.toDTO(pub));
    }
}

