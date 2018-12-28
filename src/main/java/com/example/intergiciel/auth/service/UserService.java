package com.example.intergiciel.auth.service;

import com.example.intergiciel.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
