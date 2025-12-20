package com.rush.service;

import com.rush.domain.map.Cell;
import com.rush.domain.orgaism.Organism;
import com.rush.domain.orgaism.animal.Animal;
import com.rush.domain.orgaism.plant.Plant;
import com.rush.utils.AnimalFactory;
import com.rush.utils.PlantFactory;

import java.util.List;

public class CellService {

    public void growPlants(Cell cell, Class<? extends Plant> type, int amount) {
        for (int i = 0; i < amount; i++) {
            cell.add(PlantFactory.getInstance(type));
        }
    }

    public List<Animal> getAnimals(Cell cell) {
        return cell.getByType(Animal.class);
    }

    public List<Plant> getPlants(Cell cell) {
        return cell.getByType(Plant.class);
    }

    public void addAnimal(Cell cell, Class<? extends Animal> type) {
        cell.add(AnimalFactory.getInstance(type, cell));
    }

    public void addPlants(Cell cell, Class<? extends Plant> type, int count) {
        for (int i = 0; i < count; i++) {
            cell.add(PlantFactory.getInstance(type));
        }
    }

    public void removeOrganism(Cell cell, Organism organism) {
        cell.remove(organism);
    }

    public void addOrganism(Cell targetCell, Animal animal) {
        targetCell.add(animal);
    }
}
