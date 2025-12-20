package com.rush.domain.orgaism.animal.herbivore;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;

public class Rabbit extends Herbivore {

    public Rabbit(Cell cell, AnimalConfig config) {
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
