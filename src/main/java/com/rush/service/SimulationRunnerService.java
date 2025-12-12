package com.rush.service;

import com.rush.domain.map.Cell;
import com.rush.domain.map.Island;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.plant.Plant;

public class SimulationRunnerService {
    private final Island island = Island.getInstance();

    public void run() {

        while (true) {
            printCells();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void printCells() {
        Cell[][] cells = island.getCells();

        for (int i = 0; i < cells.length; i++) {
            System.out.println("-".repeat(60));

            // First row: Animals
            for (int j = 0; j < cells[i].length; j++) {
                System.out.printf("| A:%3d ", cells[i][j].getCountByType(Animal.class));
            }
            System.out.println("|");

            // Second row: Plants
            for (int j = 0; j < cells[i].length; j++) {
                System.out.printf("| P:%3d ", cells[i][j].getCountByType(Plant.class));
            }
            System.out.println("|");
        }

        System.out.println("-".repeat(60));
    }
}
