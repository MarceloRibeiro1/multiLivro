package com.fcamara.multilivro.user.controller;

import com.fcamara.multilivro.book.service.BookService;
import com.fcamara.multilivro.user.dto.UserDTO;
import com.fcamara.multilivro.user.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class AppUserController {
    private final AppUserService userService;
    private BookService bookService;

    @GetMapping("/find-all")
    public ResponseEntity<List<UserDTO>> findAll(){
        log.info("Get request to find all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable String username){
        log.info("Get request to find a user by username :" + username);

        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        log.info("Put request to update a user : " + userDTO.getId());

        return ResponseEntity.ok(userService.update(userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){
        log.info("Delete request to delete a user : " + userId);

        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
