package com.fcamara.multilivro.user.controller;

import com.fcamara.multilivro.user.dto.AppUserCreateDto;
import com.fcamara.multilivro.user.dto.AuthenticationResponse;
import com.fcamara.multilivro.user.dto.LoginDTO;
import com.fcamara.multilivro.user.dto.UserDTO;
import com.fcamara.multilivro.user.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody AppUserCreateDto appUser){
        log.info("Post request to create user : " + appUser.getUsername());

        return ResponseEntity.ok(authService.register(appUser));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginDTO request){
        log.info("Post request to authenticate : " + request.getUsername());

        return ResponseEntity.ok(authService.authenticate(request));
    }
    @PostMapping("/reset-password")
    public ResponseEntity<UserDTO> resetPassword(@RequestBody LoginDTO request){
        log.info("Post request to reset password : " + request.getUsername());

        return ResponseEntity.ok(authService.resetPassword(request));
    }
}
