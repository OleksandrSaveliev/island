package com.rush.app;

import com.rush.config.*;
import com.rush.utils.AnimalRegistry;
import com.rush.domain.map.Island;
import com.rush.service.CellService;
import com.rush.service.AnimalService;
import com.rush.service.IslandService;

import java.util.List;

public class ApplicationStarter {

    private static MapConfig mapConfig;

    private ApplicationStarter() {
    }

    public static void start() {
        loadConfigurations();

        Island island = new Island(mapConfig.getMapWidth(), mapConfig.getMapHeight());
        CellService cellService = new CellService();
        IslandService islandService = new IslandService(island, cellService, mapConfig);
        AnimalService animalService = new AnimalService(cellService, islandService);

        WorldInitialization worldInitialization = new WorldInitialization(islandService, cellService, mapConfig);
        SimulationRunner runner = new SimulationRunner(islandService, animalService, cellService, mapConfig);

        worldInitialization.initialize();
        runner.run();
    }

    private static void loadConfigurations () {
        ConfigLoader configLoader = new ConfigLoader();
        List<AnimalConfig> animalConfigs = configLoader.loadAnimalsConfig("animals.json");
        FeedingConfig feedingConfig = configLoader.loadFeedingConfig("feeding.json");
        mapConfig = configLoader.loadMapConfig("map.json");

        AnimalRegistry.registerAnimals(animalConfigs);
        AnimalRegistry.registerFeeding(feedingConfig);
    }

}
