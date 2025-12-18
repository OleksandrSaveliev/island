package com.rush;

import com.rush.config.ConfigLoader;
import com.rush.service.SimulationRunnerService;
import com.rush.service.WorldInitializationService;

public class IslandApplication {
    public static void main(String[] args) {

        ConfigLoader.load("animals.json");

        WorldInitializationService worldInitializationService = new WorldInitializationService();
        worldInitializationService.initialize();
        SimulationRunnerService runner = new SimulationRunnerService();
        runner.run();
    }
}
