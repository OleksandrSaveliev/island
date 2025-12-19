package com.rush.domain.orgaism.animal.predator;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;

public class Bear extends Predator {
    public Bear(Cell cell, AnimalConfig config) {
        super(cell, config);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean canEat(Organism organism) {
        return false;
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void die() {

    }
}
