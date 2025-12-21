package com.rush.domain.organism;

import lombok.Getter;

@Getter
public abstract class Organism {
    private final int weight;

    public Organism(int weight) {
        this.weight = weight;
    }
}

