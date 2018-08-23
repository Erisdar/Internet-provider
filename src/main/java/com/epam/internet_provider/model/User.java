package com.epam.internet_provider.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.beans.ConstructorProperties;
import java.util.List;

@Data
public class User {
  @Column(name = "user_id")
  private int id;

  @Column(name = "login")
  private String login;

  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "bonus_amount")
  private int bonusAmount;

  @Column(name = "cash")
  private int cash;

  private Role role;
  private Status status;
  private Tariff tariff;
  private List<Reward> rewards;

}
