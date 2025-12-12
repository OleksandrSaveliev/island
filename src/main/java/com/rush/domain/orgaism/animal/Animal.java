package com.rush.domain.orgaism.animal;

import com.rush.domain.orgaism.Organism;
import com.rush.domain.map.Cell;

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
