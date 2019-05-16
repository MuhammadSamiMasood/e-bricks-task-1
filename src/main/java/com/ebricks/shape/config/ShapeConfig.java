package com.ebricks.shape.config;

public class ShapeConfig {

    private static ShapeConfig obj;

    public String shapeServiceUrl;

    private ShapeConfig() { }

    public static ShapeConfig getInstance()
    {
        if (obj==null)
            obj = new ShapeConfig();
        return obj;
    }
}
