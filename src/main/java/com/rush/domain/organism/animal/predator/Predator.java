package com.rush.domain.organism.animal.predator;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.Organism;
import com.rush.domain.organism.animal.Animal;
import com.rush.utils.AnimalRegistry;

public abstract class Predator extends Animal {
    protected Predator(Cell cell, AnimalConfig config) {
        super(cell, config);
    }

    @Override
    public boolean canEat(Organism organism) {
        if (!isHungry()) {
            return false;
        }
        if (organism instanceof Animal animal) {
            return AnimalRegistry.getProbabilityToCatch(this.getClass(), animal.getClass()) > 0;
        }
        return false;
    }
}
