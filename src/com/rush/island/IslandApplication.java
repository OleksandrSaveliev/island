package com.rush.island;

import com.rush.island.domain.map.Cell;
import com.rush.island.domain.map.Island;

public class IslandApplication {
    public static void main(String[] args) {
        Island island = new Island(10, 10);
        Cell[][] cells = island.getCells();

        for (int i = 0; i < cells.length; i++) {
            System.out.println("-".repeat(140));
            for (int j = 0; j < cells[0].length; j++) {
                System.out.printf("|%3d %3d  |   ", i, j);
            }
            System.out.println();
        }
    }
}
