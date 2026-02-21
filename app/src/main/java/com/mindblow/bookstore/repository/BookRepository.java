package com.mindblow.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindblow.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    public Page<Book> findByPublisher(Long publisherId, Pageable pageable);
}
