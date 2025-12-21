package com.rush.app;

import com.rush.utils.AnimalRegistry;
import com.rush.config.MapConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.animal.Animal;
import com.rush.domain.organism.plant.Grass;
import com.rush.service.CellService;
import com.rush.service.IslandService;

import java.util.Collections;
import java.util.Random;

public class WorldInitialization {

    private final IslandService islandService;
    private final CellService cellService;
    private final Random random = new Random();

    public WorldInitialization(IslandService islandService, CellService cellService) {
        this.islandService = islandService;
        this.cellService = cellService;
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
            int count = random.nextInt(max + 1);

            for (int i = 0; i < count; i++) {
                cellService.addAnimal(cell, type);
            }
        }
    }

    private void populatePlants(Cell cell) {
        int count = random.nextInt(MapConfig.MAX_PLANTS_COUNT + 1);
        cellService.addPlants(cell, Grass.class, count);
    }
}
