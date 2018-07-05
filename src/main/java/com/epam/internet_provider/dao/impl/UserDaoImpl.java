package com.epam.internet_provider.dao.impl;

import com.epam.internet_provider.connection.DbConnectionPool;
import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.model.User;
import io.vavr.control.Try;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);
    private static final String INSERT_NEW = "INSERT INTO internet_provider.user (login, password, email, role, status, bonus_amount) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_PASSWORD_ROLE = "SELECT password, role FROM internet_provider.user where login = ?";
    private DbConnectionPool connectionPool = DbConnectionPool.getInstance();

    @Override
    public boolean registerUser(User user) {

        Connection connection = connectionPool.getConnection();

        return Try.withResources(() -> connection, () -> connection.prepareStatement(INSERT_NEW))
                .of((connection1, preparedStatement) -> {
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getEmail());
                    preparedStatement.setInt(4, user.getRole());
                    preparedStatement.setInt(5, user.getStatus());
                    preparedStatement.setInt(6, user.getBonus_amount());
                    preparedStatement.execute();
                    return true;
                }).getOrElseGet(e -> {
                    LOG.error("Runtime exception was throw in process of registerUser: ", e);
                    return false;
                });
    }

    @Override
    public Credentials getCredentials(String login) {
        Connection connection = connectionPool.getConnection();

        return Try.withResources(() -> connection, () -> connection.prepareStatement(SELECT_PASSWORD_ROLE))
                .of((connection1, preparedStatement) -> {
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    String password = null;
                    int role = 0;
                    while (resultSet.next()) {
                        password = resultSet.getString("password");
                        role = resultSet.getInt("role");
                    }
                    return new Credentials(login, password, role);
                }).getOrElseGet(e -> null);
    }

    @Override
    public User getUser() {
        return null;
    }
}
