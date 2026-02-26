package com.mindblow.bookstore.service.impl;

import java.util.Optional;
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

    @Override
    public Optional<UserDTO> updateUser(Long id, UserSaveDTO dto) {
        Optional<User> userToUpdate = repository.findById(id);
        if (userToUpdate.isEmpty()) return Optional.empty();
        
        User entity = userToUpdate.get();
        String[] newFullName = dto.fullName().split(" ");
        entity.setFistName(newFullName[0]);
        entity.setLastName(newFullName[1]);
        entity.setEmail(dto.email());
        
        return Optional.of(mapper.toDTO(repository.save(null)));
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}

