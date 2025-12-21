package com.rush.domain.organism.animal.predator;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.Organism;
import com.rush.domain.organism.animal.Animal;
import com.rush.domain.organism.animal.herbivore.Herbivore;

public abstract class Predator extends Animal {
    protected Predator(Cell cell, AnimalConfig config) {
        super(cell, config);
    }

    @Override
    public boolean canEat(Organism organism) {
        return organism instanceof Herbivore && isHungry();
    }

    @Override
    public void eat(Organism food) {
        if (!isHungry() || !(food instanceof Herbivore herbivore)) {
            return;
        }
        double remaining = foodNeeded - fullness;
        double gained = Math.min(herbivore.getWeight(), remaining);
        setFullness(fullness + gained);
    }
}
