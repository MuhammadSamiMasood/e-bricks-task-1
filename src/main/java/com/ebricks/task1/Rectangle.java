package com.ebricks.task1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonGetter
    public double area() {
        return this.width * this.length;
    }

    @JsonGetter
    public double perimeter() {
        return 2.0 * (this.width + this.length);
    }

    public String display() {

        String rectangleString = "Length of Rectangle: " + this.length;
        String widthString = "Width of Rectangle: " + this.width;
        String areaString = "Area of Rectangle: " + this.area();
        String perimeterString = "Perimeter of Rectangle: " + this.perimeter();
        String completeString = rectangleString + "\n" + widthString + "\n" + areaString + "\n" + perimeterString;

        logger.info(rectangleString);
        logger.info(widthString);
        logger.info(areaString);
        logger.info(perimeterString);

        return completeString;
    }
}
