package com.fcamara.multilivro.user.dto;

import com.fcamara.multilivro.user.model.AppUser;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String name;
    private String email;

    public UserDTO(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
