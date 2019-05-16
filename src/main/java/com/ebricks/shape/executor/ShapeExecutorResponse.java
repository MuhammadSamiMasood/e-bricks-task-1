package com.ebricks.shape.executor;

import com.ebricks.shape.model.Shape;

public class ShapeExecutorResponse {
    private String message;
    private Shape shape;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setShape(Shape shape){
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
