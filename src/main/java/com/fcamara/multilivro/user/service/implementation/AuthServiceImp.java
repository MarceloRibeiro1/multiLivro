package com.fcamara.multilivro.user.service.implementation;

import com.fcamara.multilivro.configuration.JWT.JWTService;
import com.fcamara.multilivro.exception.BasicException;
import com.fcamara.multilivro.exception.LogLevel;
import com.fcamara.multilivro.user.dto.AppUserCreateDto;
import com.fcamara.multilivro.user.dto.AuthenticationResponse;
import com.fcamara.multilivro.user.dto.LoginDTO;
import com.fcamara.multilivro.user.dto.UserDTO;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.model.Roles;
import com.fcamara.multilivro.user.repository.AppUserRepository;
import com.fcamara.multilivro.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtTokenService;


    @Override
    public AuthenticationResponse register(AppUserCreateDto appUser) {
        AppUser user = new AppUser();

        user.setUsername(appUser.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        user.setName(appUser.getName());
        user.setEmail(appUser.getEmail());
        user.setAuthorities(Roles.USER);

        userRepository.save(user);
        String jwtToken = jwtTokenService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(LoginDTO login) {
        AppUser user = userRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new BasicException("Username or password incorrect"));

        if (!passwordEncoder.matches(login.getPassword(),user.getPassword()))
            throw new BasicException("Username or password incorrect");

        String jwtToken = jwtTokenService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public UserDTO resetPassword(LoginDTO login) {
        AppUser user = userRepository.findByUsername(login.getUsername())
                .orElseThrow(() -> new BasicException("Username or password incorrect"));
        user.setPassword(passwordEncoder.encode(login.getPassword()));

        return new UserDTO(userRepository.save(user));
    }

    @Override
    public AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username).orElseThrow(
                () -> new BasicException("Error getting current user", HttpStatus.INTERNAL_SERVER_ERROR, LogLevel.ERROR)
        );
    }
}
