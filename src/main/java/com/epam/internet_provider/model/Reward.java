package com.epam.internet_provider.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

    private int rewardId;
    private String title;
    private int bonusPoints;
    private String imgHref;

}
