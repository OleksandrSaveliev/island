package com.rush.config;

import com.rush.domain.organism.animal.Animal;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnimalRegistry {

    private static final Map<Class<? extends Animal>, AnimalConfig> CONFIGS = new HashMap<>();
    private static FeedingConfig feedingConfig;

    private static final Map<String, Class<? extends Animal>> TYPES;

    static {
        Reflections reflections = new Reflections("com.rush.domain.orgaism.animal");

        TYPES = reflections.getSubTypesOf(Animal.class)
                .stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .collect(Collectors.toMap(Class::getSimpleName, c -> c));
    }

    private AnimalRegistry() {}

    public static void registerAnimals(Iterable<AnimalConfig> configs) {
        for (AnimalConfig cfg : configs) {
            Class<? extends Animal> clazz = TYPES.get(cfg.getType());

            if (clazz == null) {
                throw new RuntimeException("Unknown animal type: " + cfg.getType());
            }

            CONFIGS.put(clazz, cfg);
        }
    }

    public static void registerFeeding(FeedingConfig feeding) {
        feedingConfig = feeding;
    }

    public static AnimalConfig getConfig(Class<? extends Animal> clazz) {
        return CONFIGS.get(clazz);
    }

    public static int getProbabilityToCatch(Class<? extends Animal> predatorType, Class<? extends Animal> victimType) {
        return feedingConfig.getProbability(predatorType, victimType);
    }

    public static Set<Class<? extends Animal>> getAllAnimalsTypes() {
        return CONFIGS.keySet();
    }

    public static int getMaxCountByType(Class<? extends Animal> clazz) {
        return CONFIGS.get(clazz).getMaxCount();
    }
}
