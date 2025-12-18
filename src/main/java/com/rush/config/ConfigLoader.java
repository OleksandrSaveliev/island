package com.rush.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rush.domain.orgaism.animal.Animal;
import org.reflections.Reflections;

import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class ConfigLoader {

    private static final Map<Class<? extends Animal>, AnimalConfig> CONFIGS = new HashMap<>();
    private static final Map<String, Class<? extends Animal>> ANIMAL_TYPES;

    static {
        Reflections reflections = new Reflections("com.rush.domain.orgaism.animal");

        ANIMAL_TYPES = reflections.getSubTypesOf(Animal.class)
                .stream()
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .collect(Collectors.toMap(Class::getSimpleName, c -> c));
    }

    private ConfigLoader() {
    }

    public static void load(String path) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(path)) {

            List<AnimalConfig> list = mapper.readValue(
                    is, new TypeReference<>() {
                    }
            );

            for (AnimalConfig cfg : list) {
                Class<? extends Animal> clazz = ANIMAL_TYPES.get(cfg.getType());
                CONFIGS.put(clazz, cfg);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configs", e);
        }
    }

    public static AnimalConfig getConfig(Class<? extends Animal> clazz) {
        return CONFIGS.get(clazz);
    }

    public static Set<Class<? extends Animal>> getAllAnimalsTypes() {
        return new HashSet<>(ANIMAL_TYPES.values());
    }

    public static int maxCount(Class<? extends Animal> clazz) {
        return CONFIGS.get(clazz).getMaxCount();
    }
}
