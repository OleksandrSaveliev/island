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
    private final CellService cellService;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(3);

    public SimulationRunner(
            IslandService islandService,
            AnimalService animalService,
            CellService cellService
    ) {
        this.islandService = islandService;
        this.animalService = animalService;
        this.cellService = cellService;
    }

    public void run() {
        startGrassGrowth();
        startPrinting();
        startAnimalActions();
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
                    Arrays.stream(islandService.getCells())
                            .flatMap(Arrays::stream)
                            .forEach(cell -> {
                                List<Animal> animals = cellService.getAnimals(cell);
                                animals.forEach(animal -> {
                                    animalService.feedAnimal(animal);
                                    animalService.moveAnimal(animal);
                                    animalService.reproduceAnimal(animal);
                                });
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
}
