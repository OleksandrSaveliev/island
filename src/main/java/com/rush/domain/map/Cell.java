package com.rush.domain.map;

import com.rush.domain.orgaism.Organism;

import java.util.ArrayList;
import java.util.List;

public class Cell {

   private final List<Organism> organisms = new ArrayList<>();

   public synchronized void add(Organism organism) {
      organisms.add(organism);
   }

   public synchronized void remove(Organism organism) {
      organisms.remove(organism);
   }

   public synchronized List<Organism> getAll() {
      return List.copyOf(organisms);
   }

   public synchronized <T extends Organism> List<T> getByType(Class<T> type) {
      return organisms.stream()
              .filter(type::isInstance)
              .map(type::cast)
              .toList();
   }

   public synchronized int count(Class<? extends Organism> type) {
      return (int) organisms.stream()
              .filter(type::isInstance)
              .count();
   }
}