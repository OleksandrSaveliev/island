package com.rush.domain.map;

import com.rush.config.MapConfig;

public class Island {

    Cell[][] cells;

    private Island(int width, int height) {
        cells = new Cell[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    private static class Holder {
        private static final Island INSTANCE =
                new Island(MapConfig.MAP_WIDTH, MapConfig.MAP_HEIGHT);
    }

    public static Island getInstance() {
        return Holder.INSTANCE;
    }
}
