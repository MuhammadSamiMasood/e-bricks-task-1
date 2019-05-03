package com.ebricks.shape.main;

import com.ebricks.shape.processor.ShapeProcessor;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Driver {

    public static void main(String[] args){

        ShapeProcessor shapeProcessor = new ShapeProcessor();

        try {
            shapeProcessor.init();
            shapeProcessor.process();
        } catch (IOException e) {
            ShapeProcessor.logger.error(e);
        } catch (InterruptedException e) {
            ShapeProcessor.logger.error(e);
        } catch (ExecutionException e) {
            ShapeProcessor.logger.error(e);
        }finally {
            try {
                shapeProcessor.end();
            } catch (InterruptedException e) {
                ShapeProcessor.logger.error(e);
            }
        }

    }
}
