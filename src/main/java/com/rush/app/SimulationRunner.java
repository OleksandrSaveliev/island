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

import static com.rush.config.MapConfig.TICK_PERIOD_IN_SECONDS;

public class SimulationRunner {

    private final IslandService islandService;
    private final AnimalService animalService;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(4);

    public SimulationRunner(
            IslandService islandService,
            AnimalService animalService
    ) {
        this.islandService = islandService;
        this.animalService = animalService;
    }

    public void run() {
        startGrassGrowth();
        startPrinting();
        startAnimalActions();
        startStopConditionCheck();
    }

    private void startGrassGrowth() {
        scheduler.scheduleAtFixedRate(
                () -> islandService.growPlantsRandomly(Grass.class, MapConfig.MAX_PLANTS_PER_TICK),
                0, TICK_PERIOD_IN_SECONDS, TimeUnit.SECONDS
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
                0, TICK_PERIOD_IN_SECONDS, TimeUnit.SECONDS
        );
    }

    private void startPrinting() {
        scheduler.scheduleAtFixedRate(
                islandService::printIslandStatistics,
                0, TICK_PERIOD_IN_SECONDS, TimeUnit.SECONDS
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
                0, TICK_PERIOD_IN_SECONDS, TimeUnit.SECONDS
        );
    }
}
