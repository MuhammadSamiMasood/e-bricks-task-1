package com.ebricks.shape.executor;


import com.ebricks.shape.model.Shape;


public abstract class ShapeExecutor {

    protected Shape shape;

    public abstract ShapeExecutorResponse execute();
}

