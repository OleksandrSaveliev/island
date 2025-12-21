package com.rush.utils;

import com.rush.domain.organism.plant.Plant;

public class PlantFactory {
    private PlantFactory() {
    }

    public static <T extends Plant> T getInstance(Class<T> tClass) {
        try {
            return tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create animal: " + tClass.getSimpleName(), e);
        }
    }
}
