package com.ebricks;

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

    public void display() {
        logger.info("Length of Rectangle.log: " + this.length);
        logger.info("Width of Rectangle.log: " + this.width);
        logger.info("Area of Rectangle.log: " + this.area());
        logger.info("perimeter of Rectangle.log: " + this.perimeter());
    }
}
