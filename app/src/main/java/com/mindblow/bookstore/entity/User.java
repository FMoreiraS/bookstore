package com.mindblow.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @Column(name = "FIRST_NAME", length = 30, nullable = false)
    private String fistName;

    @Column(name = "LAST_NAME", length = 30, nullable = false)
    private String lastName;

    @Column(name = "FULL_NAME", length = 300)
    private String fullName;

    @Column(name = "EMAIL", length = 100, unique = true) 
    private String email;

    @Column(name = "HASHED_PASSWORD", length = 100)
    private String hashedPassword;

    @OneToOne(optional = false)
    @MapsId
    private Cart cart;
}
