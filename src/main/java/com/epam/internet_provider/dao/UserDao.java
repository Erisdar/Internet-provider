package com.epam.internet_provider.dao;

import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.model.User;

public interface UserDao {

    boolean registerUser(User user);

    Credentials getCredentials(String login);

    User getUser();

}
