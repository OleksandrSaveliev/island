package com.rush.utils;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.animal.Animal;

public class AnimalFactory {
    private AnimalFactory() {
    }

    public static <T extends Animal> T getInstance(Class<T> tClass, Cell cell) {
        try {
            return tClass.getDeclaredConstructor(Cell.class).newInstance(cell);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create animal: " + tClass.getSimpleName(), e);
        }
    }
}
