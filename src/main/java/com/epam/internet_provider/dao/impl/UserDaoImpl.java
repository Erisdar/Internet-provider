package com.epam.internet_provider.dao.impl;

import com.epam.internet_provider.connection.DbConnectionPool;
import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.model.User;
import io.vavr.control.Try;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);
    private static final String INSERT_NEW = "INSERT INTO internet_provider.user " +
            "(login, password, email, role, status, bonus_amount, cash) VALUES(?,?,?,?,?,?,?)";
    private static final String SELECT_USER = "SELECT * FROM internet_provider.user where login = ?";
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
                    preparedStatement.setInt(6, user.getBonusAmount());
                    preparedStatement.setInt(7, user.getCash());
                    preparedStatement.execute();
                    return true;
                }).getOrElseGet(e -> {
                    LOG.error("Runtime exception was throw in process of registerUser: ", e);
                    return false;
                });
    }

    @Override
    public User getUser(String login) {
        Connection connection = connectionPool.getConnection();

        return Try.withResources(() -> connection, () -> connection.prepareStatement(SELECT_USER))
                .of((connection1, preparedStatement) -> {
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    User user = new User();
                    while (resultSet.next()) {
                        user.setEmail(resultSet.getString("email"));
                        user.setLogin(resultSet.getString("login"));
                        user.setPassword(resultSet.getString("password"));
                        user.setBonusAmount(resultSet.getInt("bonus_amount"));
                        user.setRole(resultSet.getInt("role"));
                        user.setStatus(resultSet.getInt("status"));
                        user.setCash(resultSet.getInt("cash"));
                    }
                    return user;
                }).getOrElseGet(e -> null);
    }
}
