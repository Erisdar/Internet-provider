package com.epam.internet_provider.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Table
@Entity
public class User {
  @Id
  @Column
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private int userId;

  @Column(nullable = false, length = 30, unique = true)
  private String login;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private int bonusAmount;

  @Column private int cash;

  @Enumerated
  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private Role role;

  @Enumerated
  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private Status status;

  @ManyToOne
  @JoinColumn(name = "tariff_id")
  private Tariff tariff;

  @ManyToMany(cascade = {CascadeType.MERGE})
  @JoinTable(
      name = "user_2reward",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "reward_id")})
  private List<Reward> rewards;
}
