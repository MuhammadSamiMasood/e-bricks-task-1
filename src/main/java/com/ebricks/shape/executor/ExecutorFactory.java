package com.ebricks.shape.executor;

import com.ebricks.shape.model.Shape;

public class ExecutorFactory {

    public ShapeExecutor getShapeExecutor(Shape shape){
        if(shape.getClass().getName().equalsIgnoreCase("com.ebricks.shape.model.Rectangle")){
            return new RectangleExecutor(shape);
        }
        else if(shape.getClass().getName().equalsIgnoreCase("com.ebricks.shape.model.Circle")){
            return new CircleExecutor(shape);
        }
        else{
            return new PentagonExecutor(shape);
        }
    }
}
