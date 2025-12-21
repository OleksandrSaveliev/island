package com.rush.service;

import com.rush.domain.map.Cell;
import com.rush.domain.map.Island;
import com.rush.domain.organism.animal.Animal;
import com.rush.domain.organism.animal.herbivore.Herbivore;
import com.rush.domain.organism.animal.predator.Predator;
import com.rush.domain.organism.plant.Plant;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class IslandService {

    @Getter
    private final Cell[][] cells;
    private final CellService cellService;

    @Getter
    private final Object islandLock = new Object();

    public IslandService(Island island, CellService cellService) {
        this.cells = island.getCells();
        this.cellService = cellService;
    }

    public void growPlantsRandomly(Class<? extends Plant> plantType, int maxAmount) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        synchronized (islandLock) {
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    int amount = random.nextInt(maxAmount + 1);
                    if (amount > 0) {
                        cellService.growPlants(cell, plantType, amount);
                    }
                }
            }
        }
    }

    public List<Animal> getAllAnimals() {
        synchronized (islandLock) {
            return Arrays.stream(cells)
                    .flatMap(Arrays::stream)
                    .flatMap(cell -> cell.getByType(Animal.class).stream())
                    .toList();
        }
    }

    public void printIsland() {
        synchronized (islandLock) {
            for (int i = 0; i < cells.length; i++) {
                System.out.println("-".repeat(60));
                for (int j = 0; j < cells[i].length; j++) {
                    Cell cell = cells[i][j];
                    System.out.printf("| A:%3d ", cell.count(Animal.class));
                    System.out.printf("| Pre:%3d ", cell.count(Predator.class));
                    System.out.printf("| Her:%3d ", cell.count(Herbivore.class));
                    System.out.printf("| P:%3d ", cell.count(Plant.class));
                }
                System.out.println("|");
            }
            System.out.println("-".repeat(80));
        }
    }

    public long countPredators() {
        return getAllAnimals().stream()
                .filter(animal -> animal instanceof Predator)
                .count();
    }

    public long countHerbivores() {
        return getAllAnimals().stream()
                .filter(animal -> animal instanceof Herbivore)
                .count();
    }

    public void printIslandStatistics() {
        synchronized (islandLock) {
            System.out.println("=== Island statistics ===");

            var animalStats = getAllAnimals().stream()
                    .collect(Collectors.groupingBy(
                                    animal -> animal.getClass().getSimpleName(),
                                    Collectors.counting()
                            )
                    );

            long totalAnimals = animalStats.values()
                    .stream()
                    .mapToLong(Long::longValue)
                    .sum();

            animalStats.forEach((type, count) ->
                    System.out.printf("%-15s : %d%n", type, count)
            );

            System.out.println("-------------------------");
            System.out.printf("%-15s : %d%n", "TOTAL ANIMALS", totalAnimals);

            long totalPlants = Arrays.stream(cells)
                    .flatMap(Arrays::stream)
                    .mapToLong(cell -> cell.count(Plant.class))
                    .sum();

            System.out.printf("%-15s : %d%n", "TOTAL PLANTS", totalPlants);

            System.out.println("=========================");
        }
    }
}
