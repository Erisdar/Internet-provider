package com.epam.internet_provider.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private Tariff tariff;
    private Role role;
    private Status status;
    private int bonusAmount;
    private int cash;
    private List<Reward> rewards;

}
