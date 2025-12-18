package com.rush.service;

import com.rush.domain.orgaism.plant.Grass;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationRunnerService {

    private final IslandService islandService;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(3);

    public SimulationRunnerService(IslandService islandService) {
        this.islandService = islandService;
    }

    public void run() {
        startGrassGrowth();
        startPrinting();
        // later: startAnimalActions();
    }

    private void startGrassGrowth() {
        scheduler.scheduleAtFixedRate(
                () -> islandService.growPlantsRandomly(Grass.class, 5),
                0, 2, TimeUnit.SECONDS
        );
    }

    private void startPrinting() {
        scheduler.scheduleAtFixedRate(
                islandService::printIsland,
                0, 1, TimeUnit.SECONDS
        );
    }
}
