package com.fcamara.multilivro.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserCreateDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    private String email;
}
