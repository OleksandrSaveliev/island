package com.rush.domain.orgaism.animal.predator;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.animal.Animal;

public abstract class Predator extends Animal {
    protected Predator(Cell cell) {
        super(cell);
    }

    @Override
    public void eat() {

    }
}
