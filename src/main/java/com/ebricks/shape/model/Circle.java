package com.ebricks.shape.model;

import com.fasterxml.jackson.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonTypeName("circle")
public class Circle extends Shape {

    public static Logger logger = LogManager.getLogger(Circle.class.getName());

    private final double PI = 3.1416;

    private double radius;

    @JsonCreator
    public Circle(@JsonProperty("radius") double r) {
        this.radius = r;
    }

    public double area() {
        return PI * this.radius * this.radius;
    }

    public double perimeter() {
        return 2 * PI * this.radius;
    }

    @JsonGetter
    public double getRadius() {
        return radius;
    }

    public Shape display() {
        logger.info(this);
        return this;
    }

    @Override
    public String toString() {
        String radiusString = "Radius of Circle: " + this.radius;
        String areaString = "Area of Circle: " + this.area();
        String circumferenceString = "Circumference of Circle: " + this.perimeter();
        String completeString = radiusString + "\n" + areaString + "\n" + circumferenceString;
        return completeString;
    }
}
