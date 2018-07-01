package com.epam.internet_provider.di_module;

import com.epam.internet_provider.dao.UserDao;
import com.epam.internet_provider.dao.impl.UserDaoImpl;
import com.google.inject.AbstractModule;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserDao.class).to(UserDaoImpl.class);
    }
}
