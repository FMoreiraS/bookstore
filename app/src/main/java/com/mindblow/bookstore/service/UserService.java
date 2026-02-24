package com.mindblow.bookstore.service;

import com.mindblow.bookstore.dto.UserDTO;
import com.mindblow.bookstore.dto.UserSaveDTO;

public interface UserService {
    public UserDTO createUser(UserSaveDTO dto);
}
