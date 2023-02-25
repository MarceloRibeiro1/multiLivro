package com.fcamara.multilivro.user.service;

import com.fcamara.multilivro.user.dto.UserDTO;
import com.fcamara.multilivro.user.model.AppUser;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    UserDTO update(UserDTO newUser);
    AppUser findUserById(UUID id);
    UserDTO findByUsername(String username);
    List<UserDTO> getAllUsers();
    void deleteUserById(UUID id);
}
