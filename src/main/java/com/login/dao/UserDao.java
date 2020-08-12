package com.login.dao;

import com.login.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public User getUser(Connection connection, String role) throws SQLException;
}
