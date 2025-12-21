package com.rush.app;

import com.rush.config.MapConfig;
import com.rush.domain.organism.animal.Animal;
import com.rush.domain.organism.plant.Grass;
import com.rush.service.AnimalService;
import com.rush.service.CellService;
import com.rush.service.IslandService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationRunner {

    private final IslandService islandService;
    private final AnimalService animalService;
    private final CellService cellService; // Re-introduced
    private final MapConfig mapConfig;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(4); // Revert to 4 threads

    public SimulationRunner(
            IslandService islandService,
            AnimalService animalService,
            CellService cellService, // Re-introduced
            MapConfig mapConfig
    ) {
        this.islandService = islandService;
        this.animalService = animalService;
        this.cellService = cellService; // Re-introduced
        this.mapConfig = mapConfig;
    }

    public void run() {
        startGrassGrowth();
        startPrinting();
        startAnimalActions();
        startStopConditionCheck();
    }

    private void startGrassGrowth() {
        scheduler.scheduleAtFixedRate(
                () -> islandService.growPlantsRandomly(Grass.class, mapConfig.getMaxPlantsPerTick()),
                0, mapConfig.getTickPeriodInSeconds(), TimeUnit.SECONDS
        );
    }

    private void startAnimalActions() {
        scheduler.scheduleAtFixedRate(
                () -> {
                    List<Animal> allAnimals = islandService.getAllAnimals();
                    allAnimals.forEach(animal -> {
                        animalService.feedAnimal(animal);
                        animalService.moveAnimal(animal);
                        animalService.reproduceAnimal(animal);
                    });
                },
                0, mapConfig.getTickPeriodInSeconds(), TimeUnit.SECONDS
        );
    }

    private void startPrinting() {
        scheduler.scheduleAtFixedRate(
                islandService::printIslandStatistics,
                0, mapConfig.getTickPeriodInSeconds(), TimeUnit.SECONDS
        );
    }

    private void startStopConditionCheck() {
        scheduler.scheduleAtFixedRate(
                () -> {
                    long predatorsCount = islandService.countPredators();
                    long herbivoresCount = islandService.countHerbivores();
                    if (predatorsCount == 0 || herbivoresCount == 0) {
                        System.out.println("Simulation stopped. All predators or herbivores are gone.");
                        scheduler.shutdown();
                    }
                },
                0, mapConfig.getTickPeriodInSeconds(), TimeUnit.SECONDS
        );
    }
}
