package com.mindblow.bookstore.mapper;

import org.springframework.stereotype.Component;

import com.mindblow.bookstore.dto.UserDTO;
import com.mindblow.bookstore.dto.UserSaveDTO;
import com.mindblow.bookstore.entity.User;

@Component
public class UserMapper {
    public User toEntity(UserSaveDTO userSaveDTO){
        User entity = new User();

        String fullName = userSaveDTO.fullName().strip();

        String[] words = fullName.split(" ");
        int wordsLength = words.length;
        String firstName = words[0];
        String lastName = words[wordsLength - 1];

        entity.setFullName(fullName);
        entity.setFistName(firstName);
        entity.setLastName(lastName);
        entity.setEmail(userSaveDTO.email());
        entity.setHashedPassword(""+userSaveDTO.password().hashCode());
        return entity;
    }

    public UserDTO toDTO(User entity){
        return new UserDTO(
            entity.getId(), 
            entity.getFistName(),
            entity.getLastName(),
            entity.getFullName(),
            entity.getEmail()
        );
    }
}

