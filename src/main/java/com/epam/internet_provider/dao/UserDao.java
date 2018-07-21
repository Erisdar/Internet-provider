package com.epam.internet_provider.dao;

import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.model.User;

public interface UserDao {

    boolean registerUser(User user);

    User getUser(String login);

    Credentials getCredentials(String login);

    boolean updateCash(String login, int cash);

    boolean updateTariff(String login, int tariff_id);

}
