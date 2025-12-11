package com.rush.island.domain.map;

import com.rush.island.domain.orgaism.Organism;

import java.util.ArrayList;
import java.util.List;

public class Cell {

   private final List<Organism> organisms = new ArrayList<>();

   public List<Organism> getOrganisms() {
      return organisms;
   }

   public void addOrganism(Organism organism) {
      this.organisms.add(organism);
   }

   public void addOrganisms(List<? extends Organism> organisms) {
      this.organisms.addAll(organisms);
   }

   public void removeOrganism(Organism organism) {
      this.organisms.remove(organism);
   }

   public long getCountByType(Class<? extends Organism> type) {
      return organisms.stream()
              .filter(type::isInstance)
              .count();
   }
}
