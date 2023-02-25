package com.fcamara.multilivro.user.service;

import com.fcamara.multilivro.user.dto.AppUserCreateDto;
import com.fcamara.multilivro.user.dto.AuthenticationResponse;
import com.fcamara.multilivro.user.dto.LoginDTO;
import com.fcamara.multilivro.user.dto.UserDTO;
import com.fcamara.multilivro.user.model.AppUser;

public interface AuthService {
    AuthenticationResponse register(AppUserCreateDto appUser);

    AuthenticationResponse authenticate(LoginDTO login);

    UserDTO resetPassword(LoginDTO login);

    AppUser getCurrentUser();
}
