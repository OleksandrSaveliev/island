package com.rush.domain.orgaism.animal.predator;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;

public abstract class Predator extends Animal {
    public Predator(Cell cell, AnimalConfig config) {
        super(cell, config);
    }

    @Override
    public void eat(Organism organism) {

    }
}
