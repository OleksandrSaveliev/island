package com.rush.utils;

import com.rush.config.AnimalConfig;
import com.rush.config.AnimalRegistry;
import com.rush.domain.map.Cell;
import com.rush.domain.organism.animal.Animal;

public class AnimalFactory {
    private AnimalFactory() {
    }

    public static <T extends Animal> T getInstance(Class<T> clazz, Cell cell) {
        try {
            return clazz.getDeclaredConstructor(Cell.class, AnimalConfig.class)
                    .newInstance(cell, AnimalRegistry.getConfig(clazz));
        } catch (Exception e) {
            throw new RuntimeException("Cannot create animal: " + clazz.getSimpleName(), e);
        }
    }
}
