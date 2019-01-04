package com.epam.internet_provider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Table
@Entity
public class Tariff {

  @Id
  @Column
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private int tariffId;

  @Column(unique = true)
  private String title;

  @Column(nullable = false)
  private int cost;

  @Column(nullable = false)
  private int downloadSpeed;

  @Column(nullable = false)
  private int uploadSpeed;

  @Column(nullable = false)
  private int traffic;

  @Column private String imgUrl;
}
