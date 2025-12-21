package com.rush.domain.organism.animal.herbivore;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.Organism;
import com.rush.domain.organism.animal.Animal;
import com.rush.domain.organism.plant.Plant;

import com.rush.utils.AnimalRegistry;

public abstract class Herbivore extends Animal {
    protected Herbivore(Cell cell, AnimalConfig config) {
        super(cell, config);
    }

    @Override
    public boolean canEat(Organism organism) {
        if (!isHungry()) {
            return false;
        }
        if (organism instanceof Plant) {
            return true;
        }
        if (organism instanceof Animal animal) {
            return AnimalRegistry.getProbabilityToCatch(this.getClass(), animal.getClass()) > 0;
        }
        return false;
    }
}
