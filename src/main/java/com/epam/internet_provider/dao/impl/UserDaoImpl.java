package com.epam.internet_provider.dao.impl;

import com.epam.internet_provider.connection.DbConnectionPool;
import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.model.User;
import io.vavr.control.Try;

import java.sql.Connection;
import java.util.function.Function;

public class UserDaoImpl implements UserDao {

    private static final String INSERT_NEW = "INSERT INTO internet_provider.user (login, password, email, role, status, bonus_amount) VALUES(?,?,?,?,?,?)";
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
                    return preparedStatement.execute();
                }).getOrElseThrow((Function<Throwable, RuntimeException>) RuntimeException::new);

    }

    @Override
    public User getUser() {
        return null;
    }
}
