package com.epam.internet_provider.service.impl;

import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.dao.impl.UserDaoImpl;
import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.model.User;
import com.epam.internet_provider.service.LoginService;
import com.epam.internet_provider.util.DecryptionUtil;
import com.epam.internet_provider.util.HashingUtil;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User authenticate(Credentials authData) {
        return Optional.ofNullable(userDao.getUser(authData.getLogin()))
                .filter(user -> HashingUtil.checkString(DecryptionUtil
                        .decryptString(authData.getPassword()), user.getPassword()))
                .orElse(null);
    }
}
