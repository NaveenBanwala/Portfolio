package com.devtiro.blog.services.impl;

import com.devtiro.blog.domain.dtos.RegisterRequest;
import com.devtiro.blog.domain.entities.User;
import com.devtiro.blog.repositories.UserRepository;
import com.devtiro.blog.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        User newUser = User.builder()
                .name(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        return userRepository.save(newUser);
    }

}
