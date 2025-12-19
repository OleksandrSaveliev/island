package com.rush.config;

import com.rush.domain.orgaism.animal.Animal;

import java.util.Map;

public class FeedingConfig {

    private Map<String, Map<String, Integer>> probabilities;

    public int getProbability(
            Class<? extends Animal> predator,
            Class<? extends Animal> victim
    ) {
        return probabilities
                .getOrDefault(predator.getSimpleName(), Map.of())
                .getOrDefault(victim.getSimpleName(), 0);
    }
}