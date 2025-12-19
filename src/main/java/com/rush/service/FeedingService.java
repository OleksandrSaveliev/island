package com.rush.service;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;

import java.util.List;

public class FeedingService {
    private final CellService cellService;

    public FeedingService(CellService cellService) {
        this.cellService = cellService;
    }

    public void feedAnimal(Animal animal, Cell cell) {
        for (Organism organism : List.copyOf(cell.getAll())) {

            if (!animal.canEat(organism)) {
                continue;
            }

            animal.eat(organism);
            cellService.removeOrganism(cell, organism);

            if (!animal.isHungry()) {
                break;
            }
        }
    }

}
