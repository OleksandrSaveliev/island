package com.rush.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class ConfigLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<AnimalConfig> loadAnimalsConfig(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            return mapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load animal configs", e);
        }
    }

    public FeedingConfig loadFeedingConfig(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            return mapper.readValue(is, FeedingConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load feeding probabilities", e);
        }
    }

    public MapConfig loadMapConfig(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            return mapper.readValue(is, MapConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load map config", e);
        }
    }
}
