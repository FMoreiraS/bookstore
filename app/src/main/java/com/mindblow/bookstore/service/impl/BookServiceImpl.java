package com.mindblow.bookstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mindblow.bookstore.dto.AuthorDTO;
import com.mindblow.bookstore.dto.BookDTO;
import com.mindblow.bookstore.dto.BookSaveDTO;
import com.mindblow.bookstore.entity.Author;
import com.mindblow.bookstore.entity.Book;
import com.mindblow.bookstore.entity.Publisher;
import com.mindblow.bookstore.exception.AuthorNotFoundException;
import com.mindblow.bookstore.exception.BookNotFoundException;
import com.mindblow.bookstore.exception.PublisherNotFoundException;
import com.mindblow.bookstore.mapper.AuthorMapper;
import com.mindblow.bookstore.mapper.BookMapper;
import com.mindblow.bookstore.repository.AuthorRepository;
import com.mindblow.bookstore.repository.BookRepository;
import com.mindblow.bookstore.repository.PublisherRepository;
import com.mindblow.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper mapper;
    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    
    @Override
    public Optional<BookDTO> findById(Long id){
        return bookRepository.findById(id).map(mapper::toDTO);

    }

    @Override
    public BookDTO save(BookSaveDTO bookSaveDTO){
        // Gather foregin keys
        Set<Long> authorIds = bookSaveDTO.authorIds();
        Long publisherId = bookSaveDTO.publisherId();
        
        // Try to find the authors. If there is any not found in the given IDS then return throw.
        List<Author> authors = authorRepository.findAllById(authorIds);
        if(authors.isEmpty()) throw new AuthorNotFoundException(authorIds);
        Set<Author> authorSet = authors.stream().collect(Collectors.toSet());

        // Try to find the publisher. If not found then throw.
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if(publisher.isEmpty()) throw new PublisherNotFoundException(publisherId);

        Book entity = mapper.toEntity(bookSaveDTO);
        entity.setAuthors(authorSet);
        entity.setPublisher(publisher.get());
        return mapper.toDTO(bookRepository.save(entity));
    }

    @Override
    public Set<BookDTO> findByAuthor(Long authorId) throws AuthorNotFoundException{
        Optional<Author> author = authorRepository.findById(authorId);
        if(author.isEmpty()) throw new AuthorNotFoundException(authorId);
        return author.get().getBooks().stream().map(mapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Optional<BookDTO> updateBook(Long bookId, BookSaveDTO bookSaveDTO){
        // Returns empty if book does not exists.
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) return Optional.empty();
    
        // Book exists. Try to update the authors.
        Book book = bookOptional.get();
        List<Author> authors = findAuthors(bookSaveDTO.authorIds());
        book.setAuthors(authors.stream().collect(Collectors.toSet()));

        // Authors updated. Try to update the publisher.
        Long publisherId = bookSaveDTO.publisherId();
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if(publisher.isEmpty()) throw new PublisherNotFoundException(publisherId);
        book.setPublisher(publisher.get());

        // Publisher updated. Return.
        return Optional.of(bookRepository.save(book)).map(mapper::toDTO);
    }

    @Override
    public Set<AuthorDTO> findBookAuthors(Long bookId){
        Optional<Book> b = bookRepository.findById(bookId);
        if(b.isEmpty()) throw new BookNotFoundException(bookId);
        Set<Author> authorEntities = b.get().getAuthors();
        return authorEntities.stream().map(authorMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Page<BookDTO> findByPublisher(Long id, PageRequest pageRequest){
        Page<Book> books = bookRepository.findByPublisher(id, pageRequest);
        return books.map(mapper::toDTO);
    }

    private List<Author> findAuthors(Set<Long> authorIds){
        // Try to find the authors. If there is any not found in the given IDS then return throw.
        List<Author> authors = authorRepository.findAllById(authorIds);
        if(authors.isEmpty()) throw new AuthorNotFoundException(authorIds);
        return authors;
    }
}

