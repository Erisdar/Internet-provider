package com.epam.internet_provider.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Table
@Entity
public class Reward {

  @Id
  @Column
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private int rewardId;

  @Column(nullable = false, unique = true)
  private String title;

  @Column(nullable = false)
  private int bonusPoints;

  @Column(nullable = false)
  private String imgHref;

  @ManyToMany(
      mappedBy = "rewards",
      cascade = {CascadeType.MERGE})
  private List<User> users;
}
