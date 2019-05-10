package com.ebricks.shape.model;

import com.fasterxml.jackson.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Rectangle extends Shape {

    public static Logger logger = LogManager.getLogger(Rectangle.class);

    private double width;
    private double length;

    @JsonCreator
    public Rectangle(@JsonProperty("width") double w, @JsonProperty("length") double l) {
        this.width = w;
        this.length = l;
    }

    public double area() {
        return this.width * this.length;
    }

    public double perimeter() {
        return 2.0 * (this.width + this.length);
    }

    @JsonGetter
    public double getLength() {
        return length;
    }

    @JsonGetter
    public double getWidth() {
        return width;
    }

    public Shape display() {
        logger.info(this);
        return this;
    }

    @Override
    public String toString() {
        String rectangleString = "Length of Rectangle: " + this.length;
        String widthString = "Width of Rectangle: " + this.width;
        String areaString = "Area of Rectangle: " + this.area();
        String perimeterString = "Perimeter of Rectangle: " + this.perimeter();
        String completeString = rectangleString + "\n" + widthString + "\n" + areaString + "\n" + perimeterString;
        return completeString;
    }
}
