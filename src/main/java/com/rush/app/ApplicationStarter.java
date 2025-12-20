package com.rush.app;

import com.rush.config.*;
import com.rush.config.AnimalRegistry;
import com.rush.domain.map.Island;
import com.rush.service.CellService;
import com.rush.service.FeedingService;
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
        FeedingService feedingService = new FeedingService(cellService);

        WorldInitialization worldInitialization = new WorldInitialization(island, cellService);
        SimulationRunner runner = new SimulationRunner(islandService, feedingService, cellService);

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
