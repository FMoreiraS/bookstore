package com.mindblow.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindblow.bookstore.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{
}
