package com.rush.service;

import com.rush.domain.map.Cell;
import com.rush.domain.map.Island;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.animal.herbivore.Rabbit;
import com.rush.domain.orgaism.animal.predator.Wolf;
import com.rush.domain.orgaism.plant.Plant;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IslandService {

    private final Cell[][] cells;
    private final CellService cellService;

    public IslandService(Island island, CellService cellService) {
        this.cells = island.getCells();
        this.cellService = cellService;
    }

    public void growPlantsRandomly(Class<? extends Plant> plantType, int maxAmount) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                int amount = random.nextInt(maxAmount + 1);
                if (amount > 0) {
                    cellService.growPlants(cell, plantType, amount);
                }
            }
        }
    }

    public List<Animal> getAllAnimals() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .flatMap(cell -> cellService.getAnimals(cell).stream())
                .toList();
    }

    public void printIsland() {

        for (int i = 0; i < cells.length; i++) {
            System.out.println("-".repeat(60));
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                System.out.printf("| W:%3d ", cell.count(Wolf.class));
                System.out.printf("| R:%3d ", cell.count(Rabbit.class));
                System.out.printf("| P:%3d ", cell.count(Plant.class));
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(60));
    }

}
