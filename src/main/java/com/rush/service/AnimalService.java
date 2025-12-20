package com.rush.service;

import com.rush.config.AnimalRegistry;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.animal.herbivore.Herbivore;
import com.rush.domain.orgaism.animal.predator.Predator;
import com.rush.shared.Direction;

import javax.swing.text.Position;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalService {
    private final CellService cellService;
    private final IslandService islandService;

    public AnimalService(CellService cellService, IslandService islandService) {
        this.cellService = cellService;
        this.islandService = islandService;
    }

    public void moveAnimal(Animal animal, Cell fromCell) {

        int[] pos = getCurrentPosition(fromCell);
        Direction direction = animal.getMoveDirection();
        int speed = animal.getSpeed();

        Cell[][] cells = islandService.getCells();

        int targetRow = pos[0];
        int targetCol = pos[1];

        switch (direction) {
            case AHEAD -> targetRow -= speed;
            case BACKWARD -> targetRow += speed;
            case LEFT -> targetCol -= speed;
            case RIGHT -> targetCol += speed;
        }

        while (targetRow < 0) {
            targetRow++;
        }
        while (targetRow >= cells.length) {
            targetRow--;
        }
        while (targetCol < 0) {
            targetCol++;
        }
        while (targetCol >= cells[0].length) {
            targetCol--;
        }

        Cell targetCell = cells[targetRow][targetCol];

        if (targetCell == fromCell) {
            return;
        }

        cellService.removeOrganism(fromCell, animal);
        cellService.addOrganism(targetCell, animal);
        animal.setCell(targetCell);
    }


    private int[] getCurrentPosition(Cell cell) {
        for (int i = 0; i < islandService.getCells().length; i++) {
            for (int j = 0; j < islandService.getCells()[i].length; j++) {
                if (islandService.getCells()[i][j] == cell) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public void feedAnimal(Animal animal, Cell cell) {
        for (Organism organism : List.copyOf(cell.getAll())) {

            if (!animal.canEat(organism)) {
                continue;
            }

            if (animal instanceof Predator predator
                    && predatorFailedToCatch(predator, organism)) {
                continue;
            }

            animal.eat(organism);
            cellService.removeOrganism(cell, organism);

            if (!animal.isHungry()) {
                return;
            }
        }
    }

    private boolean predatorFailedToCatch(Predator predator, Organism organism) {
        return organism instanceof Herbivore victim && !isVictimCaught(predator, victim);
    }

    private static boolean isVictimCaught(Predator predator, Herbivore victim) {
        int probability = AnimalRegistry.getProbabilityToCatch(
                predator.getClass(),
                victim.getClass()
        );

        return ThreadLocalRandom.current().nextInt(100) < probability;
    }
}
