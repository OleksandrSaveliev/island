package com.rush.island;

import com.rush.island.service.WorldInitializationService;

public class IslandApplication {
    public static void main(String[] args) {
        WorldInitializationService worldInitializationService = new WorldInitializationService();
        worldInitializationService.initialize();
    }
}
