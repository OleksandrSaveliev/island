package com.rush.app;

import com.rush.config.*;
import com.rush.utils.AnimalRegistry;
import com.rush.domain.map.Island;
import com.rush.service.CellService;
import com.rush.service.AnimalService;
import com.rush.service.IslandService;

import java.util.List;

public class ApplicationStarter {

    private ApplicationStarter() {
    }

    public static void start() {
        loadConfigurations();

        Island island = new Island(MapConfig.MAP_WIDTH, MapConfig.MAP_HEIGHT);
        CellService cellService = new CellService();
        IslandService islandService = new IslandService(island, cellService);
        AnimalService animalService = new AnimalService(cellService, islandService);

        WorldInitialization worldInitialization = new WorldInitialization(islandService, cellService);
        SimulationRunner runner = new SimulationRunner(islandService, animalService);

        worldInitialization.initialize();
        runner.run();
    }

    private static void loadConfigurations () {
        ConfigLoader configLoader = new ConfigLoader();
        List<AnimalConfig> animalConfigs = configLoader.loadAnimals("animals.json");
        FeedingConfig feedingConfig = configLoader.loadFeeding("feeding.json");

        AnimalRegistry.registerAnimals(animalConfigs);
        AnimalRegistry.registerFeeding(feedingConfig);
    }

}
