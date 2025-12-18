package com.rush.service;

import com.rush.config.ConfigLoader;
import com.rush.config.MapConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.map.Island;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.plant.Grass;

import java.util.Random;

public class WorldInitializationService {

    private final Island island;
    private final CellService cellService;
    private final Random random = new Random();

    public WorldInitializationService(Island island, CellService cellService) {
        this.island = island;
        this.cellService = cellService;
    }

    public void initialize() {
        for (Cell[] row : island.getCells()) {
            for (Cell cell : row) {
                populateCell(cell);
            }
        }
    }

    private void populateCell(Cell cell) {
        populateAnimals(cell);
        populatePlants(cell);
    }

    private void populateAnimals(Cell cell) {
        for (Class<? extends Animal> type : ConfigLoader.getAllAnimalsTypes()) {
            int max = ConfigLoader.maxCount(type);
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
