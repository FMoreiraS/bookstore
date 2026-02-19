package com.mindblow.bookstore.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AUTHORS")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORS_SEQ")
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}

