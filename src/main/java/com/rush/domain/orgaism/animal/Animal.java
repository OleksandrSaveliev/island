package com.rush.domain.orgaism.animal;

import com.rush.config.AnimalConfig;
import com.rush.config.ConfigLoader;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Animal extends Organism {

    protected boolean isAlive = true;
    protected Cell cell;
    protected final int weight;
    protected final int speed;
    protected final int foodNeeded;
    protected int fullness;

    protected Animal(Cell cell) {
        AnimalConfig cfg = ConfigLoader.getConfig(this.getClass());

        this.cell = cell;
        this.weight = cfg.getWeight();
        this.speed = cfg.getSpeed();
        this.foodNeeded = cfg.getFoodNeeded();
        this.fullness = foodNeeded / 2;
    }

    public abstract void move();

    public abstract void eat(Organism organism);

    public abstract boolean canEat(Organism organism);

    public abstract void reproduce();

    public abstract void die();

    public boolean isHungry() {
        return fullness == foodNeeded;
    }
}
