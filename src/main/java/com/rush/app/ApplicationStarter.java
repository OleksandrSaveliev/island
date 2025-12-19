package com.rush.app;

import com.rush.config.*;
import com.rush.config.AnimalRegistry;
import com.rush.domain.map.Island;
import com.rush.service.*;

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

        WorldInitializationService worldInitializationService = new WorldInitializationService(island, cellService);
        worldInitializationService.initialize();
        SimulationRunnerService runner = new SimulationRunnerService(islandService, feedingService, cellService);
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
