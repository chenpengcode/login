package com.login.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String dirver;
    private static String url;
    private static String username;
    private static String password;

    public static void init() throws IOException {
        Properties properties = new Properties();
        String configFile = "mysql.properties";
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        properties.load(is);
        dirver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    /*
    获取数据库连接
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName(dirver);
        con = DriverManager.getConnection(url, username, password);
        return con;
    }

    /*
    查询操作
     */
    public static ResultSet execute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /*
    更新操作
     */
    public static int execute(Connection connection, PreparedStatement preparedStatement, String sql, Object[] params) throws SQLException {
        int updateRows = 0;
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    /*
    关闭资源
     */
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        boolean flag = true;
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        return flag;
    }
}
