package com.login.service;

import com.login.dao.BaseDao;
import com.login.dao.UserDao;
import com.login.dao.UserDaoImpl;
import com.login.entity.User;

import java.sql.Connection;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public User login(String role, String userPassword) {
        // TODO Auto-generated method stub
        Connection connection = null;
        //通过业务层调用对应的具体数据库操作
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getUser(connection, role);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }
}
