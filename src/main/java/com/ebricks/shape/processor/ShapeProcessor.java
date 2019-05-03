package com.ebricks.shape.processor;

import com.ebricks.shape.shapes.Canvas;
import com.ebricks.shape.executor.ShapeExecutor;
import com.ebricks.shape.shapes.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ShapeProcessor {

    public static Logger logger = LogManager.getRootLogger();
    private ObjectMapper objectMapper = null;
    private Canvas canvasReader = null;
    private ExecutorService service = null;
    List<Future> futures = null;

    public void init() throws IOException {

        objectMapper = new ObjectMapper();
        canvasReader = (Canvas) objectMapper.readValue(new File("src\\main\\java\\com\\ebricks\\shape\\data\\JsonData"), Canvas.class);

        service = Executors.newFixedThreadPool(2);

        futures = new ArrayList<Future>();
        for (Shape shape : canvasReader.getShapes()) {
            ShapeExecutor shapeExecutor = new ShapeExecutor(shape);
            futures.add(service.submit(shapeExecutor));
        }
    }

    public void process() throws ExecutionException, InterruptedException {

        for (Future<Shape> future : futures) {
            Shape shape = future.get();
            logger.info(shape);
        }
    }

    public void end() throws InterruptedException {

        if(canvasReader != null){
            canvasReader.getShapes().clear();
        }
        if (service != null) {
            service.shutdown();
            service.awaitTermination(10, TimeUnit.SECONDS);
        }
        if (futures != null) {
            futures.clear();
        }
    }
}
