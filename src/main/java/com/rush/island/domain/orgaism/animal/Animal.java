package com.rush.island.domain.orgaism.animal;

import com.rush.island.domain.map.Cell;
import com.rush.island.domain.orgaism.Organism;

public abstract class Animal extends Organism {
    private boolean isAlive = true;
    private Cell cell;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public abstract void move();

    public abstract void eat();

    public abstract void reproduce();

    public boolean isAlive() {
        return isAlive;
    }

    public abstract void die();
}
