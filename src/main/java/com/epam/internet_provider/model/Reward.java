package com.epam.internet_provider.model;

import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

  @Column(name = "reward_id")
  private int rewardId;

  @Column(name = "title")
  private String title;

  @Column(name = "bonus_points")
  private int bonusPoints;

  @Column(name = "img_href")
  private String imgHref;
}
