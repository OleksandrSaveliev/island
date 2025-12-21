package com.rush.config;

import lombok.Data;

@Data
public class MapConfig {
    private int mapWidth;
    private int mapHeight;
    private int maxPlantsCount;
    private int maxPlantsPerTick;
    private int tickPeriodInSeconds;
}
