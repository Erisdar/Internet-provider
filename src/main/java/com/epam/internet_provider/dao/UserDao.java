package com.epam.internet_provider.dao;

import com.epam.internet_provider.model.User;

public interface UserDao {

    boolean registerUser(User user);

    User getUser(String login);

}
