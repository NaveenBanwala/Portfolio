package com.devtiro.blog.services;

import com.devtiro.blog.domain.entities.User;
import com.devtiro.blog.domain.dtos.RegisterRequest;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    User registerUser(RegisterRequest registerRequest);
}
