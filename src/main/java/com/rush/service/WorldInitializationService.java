package com.rush.service;

import com.rush.config.ConfigLoader;
import com.rush.config.MapConfig;
import com.rush.domain.map.Cell;
import com.rush.domain.map.Island;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.plant.Grass;
import com.rush.domain.orgaism.plant.Plant;
import com.rush.utils.AnimalFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldInitializationService {

    private final Island island = Island.getInstance();
    private final Random random = new Random();

    public void initialize() {
        Arrays.stream(island.getCells())
                .flatMap(Arrays::stream)
                .forEach(this::fillCell);
    }

    private void fillCell(Cell cell) {
        cell.addOrganisms(getAnimals(cell));
        cell.addOrganisms(getPlants());
    }

    private List<Animal> getAnimals(Cell cell) {
        List<Animal> animals = new ArrayList<>();
        var animalTypes = ConfigLoader.getAllAnimalsTypes();

        for (Class<? extends Animal> clazz : animalTypes) {
            int maxCount = ConfigLoader.maxCount(clazz);
            int count = random.nextInt(0, maxCount);

            for (int i = 0; i < count; i++) {
                Animal animal = AnimalFactory.getInstance(clazz, cell);
                animals.add(animal);
            }
        }

        return animals;
    }

    private List<Plant> getPlants() {
        List<Plant> plants = new ArrayList<>();
        int count = random.nextInt(0, MapConfig.MAX_PLANTS_COUNT);

        for (int i = 0; i < count; i++) {
            plants.add(new Grass());
        }

        return plants;
    }

}
