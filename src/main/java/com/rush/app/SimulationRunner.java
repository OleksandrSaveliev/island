package com.rush.app;

import com.rush.config.MapConfig;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.plant.Grass;
import com.rush.service.CellService;
import com.rush.service.FeedingService;
import com.rush.service.IslandService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationRunner {

    private final IslandService islandService;
    private final FeedingService feedingService;
    private final CellService cellService;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(3);

    public SimulationRunner(IslandService islandService, FeedingService feedingService, CellService cellService) {
        this.islandService = islandService;
        this.feedingService = feedingService;
        this.cellService = cellService;
    }

    public void run() {
        startGrassGrowth();
        startPrinting();

        while (true) {
            startAnimalActions();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startGrassGrowth() {
        scheduler.scheduleAtFixedRate(
                () -> islandService.growPlantsRandomly(Grass.class, MapConfig.MAX_PLANTS_PER_TICK),
                0, 2, TimeUnit.SECONDS
        );
    }

    private void startAnimalActions() {
        Arrays.stream(islandService.getCells())
                .flatMap(Arrays::stream)
                .forEach(cell -> {
                    List<Animal> animals = cellService.getAnimals(cell);
                    animals.forEach(animal -> feedingService.feedAnimal(animal, cell));
                });
    }

    private void startPrinting() {
        scheduler.scheduleAtFixedRate(
                islandService::printIsland,
                0, 1, TimeUnit.SECONDS
        );
    }
}
