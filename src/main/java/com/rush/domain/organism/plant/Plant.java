package com.rush.domain.organism.plant;

import com.rush.domain.organism.Organism;
import lombok.Getter;

@Getter
public abstract class Plant extends Organism {
    private final int weight;

    protected Plant(int weight) {
        this.weight = weight;
    }
}
