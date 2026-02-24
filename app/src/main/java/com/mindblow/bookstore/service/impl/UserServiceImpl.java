package com.mindblow.bookstore.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblow.bookstore.dto.UserDTO;
import com.mindblow.bookstore.dto.UserSaveDTO;
import com.mindblow.bookstore.entity.Cart;
import com.mindblow.bookstore.entity.User;
import com.mindblow.bookstore.mapper.UserMapper;
import com.mindblow.bookstore.repository.UserRepository;
import com.mindblow.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;

    @Override
    public UserDTO createUser(UserSaveDTO userSaveDTO){
        User entity = mapper.toEntity(userSaveDTO);
        Cart userCart = new Cart();
        userCart.setCartItems(Set.of());
        entity.setCart(userCart);
        return mapper.toDTO(repository.save(entity));
    }
}

