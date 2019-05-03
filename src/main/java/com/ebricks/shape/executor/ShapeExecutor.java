package com.ebricks.shape.executor;


import com.ebricks.shape.shapes.Shape;

import java.util.concurrent.Callable;

public class ShapeExecutor implements Callable<Shape> {

    private Shape shape;

    public ShapeExecutor(Shape s) {
        this.shape = s;
    }

    public Shape call() throws Exception {

        Shape s = shape.display();
        return s;
    }
}

