package com.rush.domain.orgaism.animal;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.shared.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public abstract class Animal extends Organism {
    private static final double FULLNESS_LIMIT = 0.001;

    protected boolean alive = true;
    protected Cell cell;
    protected final int weight;
    protected final int speed;
    protected final double foodNeeded;
    protected double fullness;

    protected Animal(Cell cell, AnimalConfig config) {
        this.cell = cell;
        this.weight = config.getWeight();
        this.speed = config.getSpeed();
        this.foodNeeded = config.getFoodNeeded();
        this.fullness = foodNeeded / 2;
    }

    public Direction getMoveDirection() {
        Direction[] directions = Direction.values();
        Random random = new Random();
        return directions[random.nextInt(directions.length)];
    }

    public void setFullness(double fullness) {
        this.fullness = fullness;
        if (fullness <= FULLNESS_LIMIT) {
            alive = false;
        }
    }

    public abstract void eat(Organism organism);

    public abstract boolean canEat(Organism organism);

    public abstract void reproduce();

    public boolean isHungry() {
        return fullness < foodNeeded;
    }
}
