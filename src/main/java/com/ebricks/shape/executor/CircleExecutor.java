package com.ebricks.shape.executor;

import com.ebricks.shape.model.Shape;

public class CircleExecutor extends ShapeExecutor {

    CircleExecutor(Shape shape){
        this.shape = shape;
    }

    @Override
    public ShapeExecutorResponse execute() {
        this.shape.display();

        ShapeExecutorResponse shapeExecutorResponse = new ShapeExecutorResponse();
        shapeExecutorResponse.setMessage("Circle executed successfully");
        shapeExecutorResponse.setShape(this.shape);

        return shapeExecutorResponse;
    }
}
