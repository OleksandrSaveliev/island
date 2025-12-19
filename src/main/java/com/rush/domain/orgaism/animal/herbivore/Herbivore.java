package com.rush.domain.orgaism.animal.herbivore;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.plant.Plant;

public abstract class Herbivore extends Animal {
    protected Herbivore(Cell cell) {
        super(cell);
    }

    @Override
    public boolean canEat(Organism organism) {
        return organism instanceof Plant;
    }

    @Override
    public void eat(Organism food) {
        fullness += ((Plant) food).getWeight();
    }
}
