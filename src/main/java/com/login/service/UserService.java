package com.login.service;

import com.login.entity.User;

public interface UserService {
    public User login(String role, String password);
}
