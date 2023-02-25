package com.fcamara.multilivro.user.service.implementation;

import com.fcamara.multilivro.handler.CustomException;
import com.fcamara.multilivro.user.dto.UserDTO;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.repository.AppUserRepository;
import com.fcamara.multilivro.user.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImp implements AppUserService {
    private final AppUserRepository repository;

    @Override
    public AppUser findUserById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CustomException("No such user"));
    }

    @Override
    public UserDTO findByUsername(String username) {
        return new UserDTO(
                repository.findByUsername(username)
                        .orElseThrow(() -> new CustomException("No such user"))
        );
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(UUID id) {
        repository.deleteById(id);
    }


    @Override
    public UserDTO update(UserDTO edditedUser) {
        AppUser user = repository.findById(edditedUser.getId())
                .orElseThrow(() -> new CustomException("No such user"));


        user.setUsername(edditedUser.getUsername());
        user.setEmail(edditedUser.getEmail());
        user.setName(edditedUser.getName());
        return new UserDTO(repository.save(user));
    }
}
