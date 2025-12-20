package com.rush.domain.orgaism.animal.predator;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;

public class Wolf extends Predator {

    public Wolf(Cell cell, AnimalConfig config) {
        super(cell, config);
    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void die() {

    }
}
