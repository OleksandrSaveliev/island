package com.rush.domain.orgaism.animal;

import com.rush.config.AnimalConfig;
import com.rush.config.ConfigLoader;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.exception.NoSuchConfigException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Animal extends Organism {
    private int weight;
    private int maxCount;
    private int speed;
    private int foodNeeded;
    private boolean isAlive = true;
    private Cell cell;

    protected Animal(Cell cell) {
        this.cell = cell;

        AnimalConfig config = ConfigLoader.getConfig(this.getClass().getSimpleName());

        if (config == null) {
            throw new NoSuchConfigException("Animal config not found for class " + this.getClass().getSimpleName());
        }

        this.weight = config.getWeight();
        this.maxCount = config.getMaxCount();
        this.speed = config.getSpeed();
        this.foodNeeded = config.getFoodNeeded();
    }

    public abstract void move();

    public abstract void eat();

    public abstract void reproduce();

    public abstract void die();
}
