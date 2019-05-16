package com.ebricks.shape.main;

import com.ebricks.shape.processor.ShapeProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class Driver {

    public static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {

        ShapeProcessor shapeProcessor = new ShapeProcessor();

        try {
            shapeProcessor.init();
            shapeProcessor.process();
        } catch (IOException | InterruptedException | ExecutionException e) {
            logger.error(e);
        } finally {
            try {
                shapeProcessor.end();
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }

    }
}
