package com.rush.app;

import com.rush.config.MapConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.animal.Animal;
import com.rush.domain.organism.plant.Grass;
import com.rush.service.CellService;
import com.rush.service.IslandService;
import com.rush.utils.AnimalRegistry;

import java.util.Collections;
import java.util.Random;

public class WorldInitialization {

    private final IslandService islandService;
    private final CellService cellService;
    private final MapConfig mapConfig;
    private final Random random = new Random();

    public WorldInitialization(IslandService islandService, CellService cellService, MapConfig mapConfig) {
        this.islandService = islandService;
        this.cellService = cellService;
        this.mapConfig = mapConfig;
    }

    public void initialize() {
        for (Cell[] row : islandService.getCells()) {
            for (Cell cell : row) {
                populateCell(cell);
            }
        }
    }

    private void populateCell(Cell cell) {
        populateAnimals(cell);
        populatePlants(cell);
        Collections.shuffle(cellService.getAnimals(cell));
    }

    private void populateAnimals(Cell cell) {
        for (Class<? extends Animal> type : AnimalRegistry.getAllAnimalsTypes()) {
            int max = AnimalRegistry.getMaxCountByType(type);
            int count = random.nextInt(max / 2);

            for (int i = 0; i < count; i++) {
                cellService.addAnimal(cell, type);
            }
        }
    }

    private void populatePlants(Cell cell) {
        int count = random.nextInt(mapConfig.getMaxPlantsCount() + 1);
        cellService.addPlants(cell, Grass.class, count);
    }
}
