package com.login.dao;

import com.login.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    public User getUser(Connection connection, String role) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        if (null != connection) {
            String sql = "select * from user where role = ?";
            Object[] params = {role};
            resultSet = BaseDao.execute(connection, preparedStatement, resultSet, sql, params);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setRole(resultSet.getString("role"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            BaseDao.closeResource(connection, null, resultSet);
        }
        return user;
    }
}
