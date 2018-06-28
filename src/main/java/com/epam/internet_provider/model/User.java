package com.epam.internet_provider.model;

import lombok.Data;

@Data
public class User {

    private String login;
    private String password;
    private String role;
    private String email;

}
