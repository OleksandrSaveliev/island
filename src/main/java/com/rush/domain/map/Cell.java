package com.rush.domain.map;

import com.rush.domain.orgaism.Organism;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cell {

   private final List<Organism> organisms = new ArrayList<>();

    public void addOrganism(Organism organism) {
      this.organisms.add(organism);
   }

   public void addOrganisms(List<? extends Organism> organisms) {
      this.organisms.addAll(organisms);
   }

   public void removeOrganism(Organism organism) {
      this.organisms.remove(organism);
   }

   public int getCountByType(Class<? extends Organism> type) {
      return (int) organisms.stream()
              .filter(type::isInstance)
              .count();
   }
}
