package com.mindblow.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.AuthorSaveDTO;
import com.mindblow.bookstore.service.AuthorService;

@SpringBootTest
public class AuthorControllerTest {
    @InjectMocks
    private AuthorController controller;

    @MockitoBean
    private AuthorService service;

    private RestTestClient client;
    private AuthorDTO authorDTO;
    
    @BeforeEach void setUp(WebApplicationContext webApplicationContext){
        client = RestTestClient.bindToApplicationContext(webApplicationContext).build();
        authorDTO = new AuthorDTO(99L, "Manoel Gomes");
    }

    @Test void shouldReturn200WhenAuthorFound(){
        // Arrange 
        given(service.findById(99L)).willReturn(Optional.of(authorDTO));

        // Act
        client.get().uri("/authors/99").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(AuthorDTO.class)
            .consumeWith(response ->{
                // Assert
                AuthorDTO dto = response.getResponseBody();
                assertNotNull(dto);
                assertEquals(99L, dto.id());
                assertEquals("Manoel Gomes",dto.name());
            });
    }
    @Test void shouldReturn404StatusAndProblemDetailWhenAuthorNotFound(){
        // Arrange
        given(service.findById(anyLong())).willReturn(Optional.empty());

        // Act
        client.get().uri("/authors/3434").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ProblemDetail.class)
            .consumeWith(response ->{
                // Assert
                ProblemDetail detail = response.getResponseBody();
                assertNotNull(detail);
                assertEquals("Not able to find author with the given id=3434", detail.getDetail());
            });
    }

    @Test void shouldReturn200WhenUpdatedAuthor(){// Maybe unnecessary test
        AuthorDTO authorDTO = new AuthorDTO(99L, "Zezinho");
        given(service.update(eq(99L), any())).willReturn(Optional.of(authorDTO));

        AuthorSaveDTO updateDTO = new AuthorSaveDTO("Zezinho");
        client.put().uri("/authors/99").body(updateDTO)
            .exchange()
            .expectStatus().isOk()
            .expectBody(AuthorDTO.class)
            .consumeWith(response -> {
                AuthorDTO dto = response.getResponseBody();
                assertNotNull(dto);
                assertEquals("Zezinho", dto.name());
            });
    }

    @Test void shouldReturn415StatusAndDetailWhenSuppliedWithNonValidJSON(){
        // Act 
        client.post().uri("/authors").body("bad data")// Given this wrong data, a problem must be thrown
            .exchange()
            // HttpMediaTypeNotSuportedException must be handled in a Controller Advice.
            .expectStatus().is4xxClientError()
            .expectBody(ProblemDetail.class)
            .consumeWith(response ->{
                ProblemDetail problemDetail = response.getResponseBody();
                HttpStatusCode s = response.getStatus();

                // Assert
                assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, s);
                assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), problemDetail.getStatus());
            });
    }
}

