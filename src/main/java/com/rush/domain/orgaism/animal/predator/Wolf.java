package com.rush.domain.orgaism.animal.predator;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;

public class Wolf extends Predator {

    public Wolf(Cell cell) {
        super(cell);
    }

    @Override
    public void move() {

    }

    @Override
    public boolean canEat(Organism organism) {
        return false;
    }

    @Override
    public void eat(Organism organism) {

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
