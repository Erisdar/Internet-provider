package com.epam.internet_provider.service;

import com.epam.internet_provider.model.Credentials;
import com.epam.internet_provider.model.User;

public interface LoginService {

    User authenticate(Credentials credentials);

}
