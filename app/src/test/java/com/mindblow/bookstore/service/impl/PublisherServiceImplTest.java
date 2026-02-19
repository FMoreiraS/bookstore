package com.mindblow.bookstore.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.mindblow.bookstore.dto.PublisherDTO;
import com.mindblow.bookstore.dto.PublisherSaveDTO;
import com.mindblow.bookstore.entity.Publisher;
import com.mindblow.bookstore.repository.PublisherRepository;
import com.mindblow.bookstore.service.PublisherService;

@SpringBootTest
public class PublisherServiceImplTest {
    @Autowired
    private PublisherService service; 
    @MockitoBean
    private PublisherRepository repository;

    private PublisherSaveDTO saveDTO;

    private Publisher entity;

    @BeforeEach void setUp(){
        saveDTO = new PublisherSaveDTO("Editora Abril");
        entity = new Publisher();
        entity.setName("Editora Abril");
    }

    @Test void shouldSavePublisher(){
        given(repository.save(any())).willAnswer((answer) -> answer.getArgument(0));

        PublisherDTO dto = service.save(saveDTO);

        assertNotNull(dto);
        assertEquals(dto.name(), "Editora Abril");
    }

    @Test void shouldFindById(){
        given(repository.findById(69L)).willReturn(Optional.of(entity));

        Optional<PublisherDTO> dto = service.findById(69L);
        
        assertNotNull(dto);
        assertTrue(dto.isPresent());
        assertEquals(dto.get().name(), "Editora Abril");
    }

    @Test void shouldUpdatePublisher(){
        given(repository.findById(69L)).willReturn(Optional.of(entity));
        given(repository.save(any())).willAnswer(ans -> ans.getArgument(0));
        PublisherSaveDTO updateDTO = new PublisherSaveDTO("Editora Fecha");

        PublisherDTO dto =  assertDoesNotThrow(() -> service.update(69L, updateDTO).get());

        assertEquals("Editora Fecha", dto.name());

    }

    
}
