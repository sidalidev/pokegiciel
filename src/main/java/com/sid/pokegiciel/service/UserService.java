package com.sid.pokegiciel.service;

import com.sid.pokegiciel.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
