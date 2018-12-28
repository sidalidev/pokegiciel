package com.example.intergiciel.service;

import com.example.intergiciel.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
