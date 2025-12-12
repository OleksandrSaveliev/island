package com.rush.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigLoader {
    private static Map<String, AnimalConfig> configMap;

    public static void loadConfigs(String path) throws Exception {
        InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(path);
        ObjectMapper mapper = new ObjectMapper();
        List<AnimalConfig> configs = mapper.readValue(
                is,
                new TypeReference<>() {
                }
        );

        configMap = configs.stream()
                .collect(Collectors.toMap(AnimalConfig::getType, cfg -> cfg));
    }

    public static AnimalConfig getConfig(String type) {
        return configMap.get(type);
    }
}