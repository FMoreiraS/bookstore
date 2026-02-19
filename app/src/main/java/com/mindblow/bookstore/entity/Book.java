package com.mindblow.bookstore.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKS_SEQ")
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Column(name = "NAME", nullable= false)
    private String name;

    @Column(name = "SYNOPSIS", nullable = false)
    private String synopsis; 

    @Column(name = "PRICE", nullable = false)
    private Long price;

    @Column(name = "OLD_PRICE")
    private Long oldPrice;

    @Column(name = "YEAR", nullable = false)
    private Long year;

    @Column(name = "ISSN", nullable = false)
    private String issn;

    @ManyToMany
    @JoinTable(name = "BOOKS_AUTHORS")
    private Set<Author> authors;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;
}

