package com.rush.domain.organism.animal;

import com.rush.config.AnimalConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.Organism;
import com.rush.shared.Direction;
import com.rush.utils.AnimalFactory;
import com.rush.utils.AnimalRegistry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
        this.fullness = Math.min(fullness, foodNeeded);
        if (this.fullness <= FULLNESS_LIMIT) {
            alive = false;
        }
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public final void eat(Organism organism) {
        if (!isHungry()) {
            return;
        }
        double remaining = foodNeeded - fullness;
        double gained = Math.min(organism.getWeight(), remaining);
        setFullness(fullness + gained);
    }

    public abstract boolean canEat(Organism organism);

    public void reproduce() {
        if (isReadyToReproduce()) {
            List<? extends Animal> animalsInCell = cell.getByType(this.getClass());
            if (animalsInCell.size() > 1 && animalsInCell.size() < AnimalRegistry.getMaxCountByType(this.getClass())) {
                if (ThreadLocalRandom.current().nextBoolean()) {
                    Animal newAnimal = AnimalFactory.getInstance(this.getClass(), cell);
                    cell.add(newAnimal);
                }
            }
        }
    }

    private boolean isReadyToReproduce() {
        return fullness >= foodNeeded * 0.8;
    }

    public boolean isHungry() {
        return fullness < foodNeeded;
    }
}
