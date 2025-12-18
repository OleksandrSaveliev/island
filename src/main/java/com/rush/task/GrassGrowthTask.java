package com.rush.task;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;

import java.util.concurrent.atomic.AtomicInteger;

public class GrassGrowthTask implements Runnable {

    private final Cell[][] cells;
    private final AtomicInteger growthAmount;
    private final Class<? extends Organism> plantType;

    public GrassGrowthTask(Cell[][] cells, int growthAmount, Class<? extends Organism> plantType) {
        this.cells = cells;
        this.growthAmount = new AtomicInteger(growthAmount);
        this.plantType = plantType;
    }

    @Override
    public void run() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.growPlants(growthAmount, plantType);
            }
        }
    }
}
