package com.rush.service;

import com.rush.utils.AnimalRegistry;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.Organism;
import com.rush.domain.organism.animal.Animal;
import com.rush.domain.organism.animal.herbivore.Herbivore;
import com.rush.domain.organism.animal.predator.Predator;
import com.rush.shared.Direction;
import com.rush.shared.Position;

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

        Position position = new Position(fromCell.getRow(), fromCell.getCol());
        Direction direction = animal.getMoveDirection();
        int speed = animal.getSpeed();

        Cell targetCell = getTargetCell(position, direction, speed);

        if (targetCell == fromCell) {
            return;
        }

        cellService.removeOrganism(fromCell, animal);
        cellService.addOrganism(targetCell, animal);
        animal.setCell(targetCell);

        decreaseFullness(animal);

        if (!animal.isAlive()) {
            cellService.removeOrganism(targetCell, animal);
        }
    }

    private Cell getTargetCell(Position position, Direction direction, int speed) {
        Cell[][] cells = islandService.getCells();

        int targetRow = position.row();
        int targetCol = position.col();

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

        return cells[targetRow][targetCol];
    }

    public void feedAnimal(Animal animal, Cell cell) {

        for (Organism organism : List.copyOf(cell.getAll())) {

            if (!animal.isAlive()) {
                break;
            }

            if (!animal.canEat(organism)) {
                continue;
            }

            if (animalFailedToCatch(animal, organism)) {
                continue;
            }

            animal.eat(organism);
            cellService.removeOrganism(cell, organism);
        }
    }

    private boolean animalFailedToCatch(Animal animal, Organism organism) {
        return organism instanceof Animal victim && !isVictimCaught(animal, victim);
    }

    private static boolean isVictimCaught(Animal animal, Animal victim) {
        int probability = AnimalRegistry.getProbabilityToCatch(
                animal.getClass(),
                victim.getClass()
        );

        return ThreadLocalRandom.current().nextInt(100) < probability;
    }

    private void decreaseFullness(Animal animal) {
        animal.setFullness(animal.getFullness() * 0.9);
    }

    public void reproduceAnimal(Animal animal, Cell cell) {
        animal.reproduce();
    }
}
