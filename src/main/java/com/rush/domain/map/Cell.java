package com.rush.domain.map;

import com.rush.domain.organism.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {

    private final List<Organism> organisms = new ArrayList<>();
    private final int row;
    private final int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

   public void add(Organism organism) {
      organisms.add(organism);
   }

   public void remove(Organism organism) {
      organisms.remove(organism);
   }

   public List<Organism> getAll() {
      return List.copyOf(organisms);
   }

   public <T extends Organism> List<T> getByType(Class<T> type) {
      return organisms.stream()
              .filter(type::isInstance)
              .map(type::cast)
              .collect(Collectors.toList());
   }

   public int count(Class<? extends Organism> type) {
      return (int) organisms.stream()
              .filter(type::isInstance)
              .count();
   }
}