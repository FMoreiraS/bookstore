package com.mindblow.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblow.bookstore.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
