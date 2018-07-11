package com.epam.internet_provider.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private int role;
    private int status;
    private int bonusAmount;
    private int cash;

}
