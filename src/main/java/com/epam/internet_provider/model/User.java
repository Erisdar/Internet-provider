package com.epam.internet_provider.model;

import lombok.Data;

@Data
public class User {

    private String login;
    private String password;
    private String email;
    private int role;
    private int status;
    private int bonus_amount;

}
