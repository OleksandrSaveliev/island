package com.rush.domain.orgaism.animal.predator;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.animal.herbivore.Herbivore;
import com.rush.domain.orgaism.plant.Plant;

public abstract class Predator extends Animal {
    protected Predator(Cell cell, AnimalConfig config) {
        super(cell, config);
    }

    @Override
    public boolean canEat(Organism organism) {

        return organism instanceof Herbivore && fullness < foodNeeded;
    }

    @Override
    public void eat(Organism food) {
        fullness += ((Herbivore) food).getWeight();
        if (fullness > foodNeeded) {
            fullness = foodNeeded;
        }
    }
}
