package com.ebricks.shape.shapes;

import com.ebricks.shape.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Canvas {

    private List<Shape> shapes = new ArrayList();

    public List<Shape> getShapes() {
        return this.shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
