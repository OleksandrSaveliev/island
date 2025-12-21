package com.rush.domain.map;

import lombok.Getter;

@Getter
public class Island {
    private final Cell[][] cells;

    public Island(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

}
