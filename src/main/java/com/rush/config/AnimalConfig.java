package com.rush.config;

import lombok.Data;

@Data
public class AnimalConfig {
    private String type;
    private int weight;
    private int maxCount;
    private int speed;
    private double foodNeeded;
}
