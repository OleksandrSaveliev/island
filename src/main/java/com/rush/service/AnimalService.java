package com.rush.service;

import com.rush.config.AnimalRegistry;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.animal.herbivore.Herbivore;
import com.rush.domain.orgaism.animal.predator.Predator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalService {
    private final CellService cellService;
    private final IslandService islandService;

    public AnimalService(CellService cellService, IslandService islandService) {
        this.cellService = cellService;
        this.islandService = islandService;
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
