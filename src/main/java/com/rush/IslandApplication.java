package com.rush;

import com.rush.config.ConfigLoader;
import com.rush.config.MapConfig;
import com.rush.domain.map.Island;
import com.rush.service.CellService;
import com.rush.service.IslandService;
import com.rush.service.SimulationRunnerService;
import com.rush.service.WorldInitializationService;

public class IslandApplication {
    public static void main(String[] args) {

        ConfigLoader.load("animals.json");

        Island island = new Island(MapConfig.MAP_WIDTH, MapConfig.MAP_HEIGHT);
        CellService cellService = new CellService();
        IslandService islandService = new IslandService(island, cellService);

        WorldInitializationService worldInitializationService = new WorldInitializationService(island, cellService);
        worldInitializationService.initialize();
        SimulationRunnerService runner = new SimulationRunnerService(islandService);
        runner.run();
    }
}
