package com.ebricks.shape.processor;

import com.ebricks.shape.model.Canvas;
import com.ebricks.shape.executor.ShapeExecutor;
import com.ebricks.shape.model.Circle;
import com.ebricks.shape.model.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ShapeProcessor {

    public static Logger logger = LogManager.getRootLogger();
    private ObjectMapper objectMapper = null;
    private Canvas canvasReader = null;
    private ExecutorService service = null;
    List<Future> futures = null;
    String shapesJson = "";
    String shapeJsonToPost;
    ShapeService shapeService = new ShapeService();

    public void init() throws IOException {

        shapesJson = (String)shapeService.get();

        objectMapper = new ObjectMapper();
        canvasReader = (Canvas) objectMapper.readValue(shapesJson, Canvas.class);

        service = Executors.newFixedThreadPool(2);

        futures = new ArrayList<Future>();
    }

    public void process() throws ExecutionException, InterruptedException, IOException {

        for (Shape shape : canvasReader.getShapes()) {
            ShapeExecutor shapeExecutor = new ShapeExecutor(shape);
            futures.add(service.submit(shapeExecutor));
        }

        for (Future<Shape> future : futures) {
            Shape shape = future.get();
            logger.info(shape);
        }

        Shape randomShape = new Circle(50.4);
        shapeJsonToPost = objectMapper.writeValueAsString(randomShape);

        String postResponse = (String)shapeService.post(shapeJsonToPost);
        logger.info(postResponse);
    }

    public void end() throws InterruptedException {

        if (service != null) {
            service.shutdown();
            service.awaitTermination(10, TimeUnit.SECONDS);
        }

    }


}
