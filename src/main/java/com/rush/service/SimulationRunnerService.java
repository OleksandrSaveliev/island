package com.rush.service;

import com.rush.domain.map.Cell;
import com.rush.domain.map.Island;
import com.rush.domain.orgaism.animal.herbivore.Rabbit;
import com.rush.domain.orgaism.animal.predator.Wolf;
import com.rush.domain.orgaism.plant.Grass;
import com.rush.domain.orgaism.plant.Plant;
import com.rush.task.GrassGrowthTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationRunnerService {
    private final Island island = Island.getInstance();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void run() {

        startGrassGrowth();

        while (true) {
            printCells();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startGrassGrowth() {
        GrassGrowthTask grassTask = new GrassGrowthTask(
                island.getCells(),
                1,
                Grass.class
        );
        scheduler.scheduleAtFixedRate(grassTask, 0, 2, TimeUnit.SECONDS);
    }

    private void printCells() {
        Cell[][] cells = island.getCells();

        for (int i = 0; i < cells.length; i++) {
            System.out.println("-".repeat(60));

            // First row: Animals
            for (int j = 0; j < cells[i].length; j++) {
                System.out.printf("| W:%3d ", cells[i][j].getCountByType(Wolf.class));
                System.out.printf("| R:%3d ", cells[i][j].getCountByType(Rabbit.class));
                System.out.printf("| P:%3d ", cells[i][j].getCountByType(Plant.class));
                System.out.printf("     ");
            }
            System.out.println("|");
        }

        System.out.println("-".repeat(60));
    }
}
