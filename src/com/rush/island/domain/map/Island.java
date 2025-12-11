package com.rush.island.domain.map;

public class Island {
    private Cell[][] cells;

    public Island(int width, int height) {
        cells = new Cell[width][height];
    }

    public Cell[][] getCells() {
        return cells;
    }
}
