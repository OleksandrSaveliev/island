package com.rush.island.service;

import com.rush.island.config.MapConfig;
import com.rush.island.domain.map.Cell;
import com.rush.island.domain.map.Island;
import com.rush.island.domain.orgaism.plant.Grass;
import com.rush.island.domain.orgaism.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class WorldInitializationService {

    private final Island island = Island.getInstance();
    private final Random random = new Random();

    public void initialize() {
        initializePlants();
    }

    private void initializePlants() {
        Cell[][] cells = island.getCells();
        for (Cell[] cell : cells) {
            IntStream.range(0, cells[0].length).forEach(it -> cell[it].addOrganisms(getPlantsForCell()));
        }
    }

    private List<Plant> getPlantsForCell() {
        List<Plant> plants = new ArrayList<>();
        int count = random.nextInt(MapConfig.MAX_PLANTS_COUNT + 1);

        for (int i = 0; i < count; i++) {
            plants.add(new Grass());
        }

        return plants;
    }

}
