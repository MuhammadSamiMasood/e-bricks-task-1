package com.ebricks.shape.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ShapeConfig {

    private static ShapeConfig instance;

    private String shapeServiceUrl;

    public String getShapeServiceUrl() {
        return shapeServiceUrl;
    }

    private ShapeConfig() { }

    public static ShapeConfig getInstance() throws IOException {
        if (instance==null)
            instance = (ShapeConfig) (new ObjectMapper().readValue(new File("resources/config.json"), ShapeConfig.class));
        return instance;
    }
}
