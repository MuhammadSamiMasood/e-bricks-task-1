package com.ebricks.shape.executor;

import com.ebricks.shape.model.Shape;

public class PentagonExecutor extends ShapeExecutor {

    PentagonExecutor(Shape shape){
        this.shape = shape;
    }

    @Override
    public ShapeExecutorResponse execute() {
        this.shape.display();

        ShapeExecutorResponse shapeExecutorResponse = new ShapeExecutorResponse();
        shapeExecutorResponse.setMessage("Pentagon executed successfully");
        shapeExecutorResponse.setShape(this.shape);

        return shapeExecutorResponse;
    }
}
