package com.rush.island.service;

import com.rush.island.config.MapConfig;
import com.rush.island.domain.map.Cell;
import com.rush.island.domain.map.Island;
import com.rush.island.domain.orgaism.plant.Grass;
import com.rush.island.domain.orgaism.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldInitializationService {

    private final Island island = Island.getInstance();
    private final Random random = new Random();

    public void initialize() {
        Cell[][] cells = island.getCells();
        initializePlants();

        for (int i = 0; i < cells.length; i++) {
            System.out.println("-".repeat(140));
            for (int j = 0; j < cells[0].length; j++) {
                System.out.printf("|%3d  |  ", cells[i][j].getCountByType(Plant.class));
            }
            System.out.println();
        }
    }

    private void initializePlants() {
        Cell[][] cells = island.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                System.out.println(123);
                System.out.println(cells[i][j].getCountByType(Plant.class));
                cells[i][j].addOrganisms(getPlantsForCell());
            }
        }
    }

    private List<Plant> getPlantsForCell() {
        List<Plant> plants = new ArrayList<>();
        int count = random.nextInt(MapConfig.MAX_PLANTS_COUNT);

        for (int i = 0; i < count; i++) {
            plants.add(new Grass());
        }

        return plants;
    }

}
