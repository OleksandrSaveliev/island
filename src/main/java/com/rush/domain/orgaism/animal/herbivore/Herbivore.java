package com.rush.domain.orgaism.animal.herbivore;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.animal.Animal;

public abstract class Herbivore extends Animal {
    protected Herbivore(Cell cell) {
        super(cell);
    }
}
