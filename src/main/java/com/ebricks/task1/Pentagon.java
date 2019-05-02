package com.ebricks.task1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pentagon extends Shape {

    public static Logger logger = LogManager.getLogger(Pentagon.class);

    private double apothem;
    private double side;

    @JsonCreator
    public Pentagon(@JsonProperty("apothem") double a, @JsonProperty("side") double s) {
        this.apothem = a;
        this.side = s;
    }

    @JsonGetter
    public double area() {
        return 5.0 * 0.5 * this.apothem * this.side;
    }

    @JsonGetter
    public double perimeter() {
        return 5.0 * this.side;
    }

    public String display() {

        String apothemString = "Apothem of the Pentagon: " + this.apothem;
        String sideString = "One side of the Pentagon: " + this.side;
        String areaString = "Area of the Pentagon: " + this.area();
        String perimeterString = "Perimeter of the Pentagon: " + this.perimeter();
        String completeString = apothemString + "\n" + sideString + "\n" + areaString + "\n" + perimeterString;

        logger.info(apothemString);
        logger.info(sideString);
        logger.info(areaString);
        logger.info(perimeterString);
        return completeString;
    }
}
