package com.rush.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.rush.domain.organism.animal.Animal;

import java.util.HashMap;
import java.util.Map;

public class FeedingConfig {

    private final Map<String, Map<String, Integer>> probabilities = new HashMap<>();

    @JsonAnySetter
    public void addPredator(String predator, Map<String, Integer> victims) {
        probabilities.put(predator, victims);
    }

    public int getProbability(
            Class<? extends Animal> predator,
            Class<? extends Animal> victim
    ) {
        return probabilities
                .getOrDefault(predator.getSimpleName(), Map.of())
                .getOrDefault(victim.getSimpleName(), 0);
    }
}