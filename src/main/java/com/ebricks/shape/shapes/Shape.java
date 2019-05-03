package com.ebricks.shape.shapes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class Shape {

    public abstract double area();

    public abstract double perimeter();

    public abstract Shape display();
}