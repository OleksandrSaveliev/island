package com.rush.domain.orgaism.plant;

import com.rush.domain.orgaism.Organism;
import lombok.Getter;

@Getter
public abstract class Plant extends Organism {
    private final int weight;

    protected Plant(int weight) {
        this.weight = weight;
    }
}
