package com.epam.internet_provider.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tariff {

    private int id;
    private String title;
    private int cost;
    private int downloadSpeed;
    private int uploadSpeed;
    private int traffic;

}
