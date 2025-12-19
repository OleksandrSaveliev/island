package com.rush.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class ConfigLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<AnimalConfig> loadAnimals(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            return mapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load animal configs", e);
        }
    }

    public FeedingConfig loadFeeding(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            return mapper.readValue(is, FeedingConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load feeding probabilities", e);
        }
    }
}
