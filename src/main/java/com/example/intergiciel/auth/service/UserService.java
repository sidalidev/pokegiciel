package com.example.intergiciel.auth.service;

import com.example.intergiciel.auth.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
