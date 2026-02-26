package com.mindblow.bookstore.service;

import java.util.Optional;

import com.mindblow.bookstore.dto.UserDTO;
import com.mindblow.bookstore.dto.UserSaveDTO;

public interface UserService {
    public UserDTO createUser(UserSaveDTO dto);
    public Optional<UserDTO> updateUser(Long id, UserSaveDTO dto);
    public void deleteUser(Long id);
}
