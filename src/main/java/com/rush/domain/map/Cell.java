package com.rush.domain.map;

import com.rush.domain.orgaism.Organism;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Cell {

   private final List<Organism> organisms = new ArrayList<>();

   public synchronized void addOrganism(Organism organism) {
      this.organisms.add(organism);
   }

   public synchronized void growPlants(AtomicInteger amount, Class<? extends Organism> plantClass) {
      for (int i = 0; i < amount.get() ; i++) {
         try {
            Organism plant = plantClass.getDeclaredConstructor().newInstance();
            organisms.add(plant);
         } catch (Exception e) {
            throw new RuntimeException("Failed to create plant instance", e);
         }
      }
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
