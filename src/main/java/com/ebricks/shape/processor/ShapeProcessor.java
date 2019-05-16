package com.ebricks.shape.processor;

import com.ebricks.shape.executor.ExecutorFactory;
import com.ebricks.shape.executor.ShapeExecutorResponse;
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

    public static Logger logger = LogManager.getLogger(ShapeProcessor.class);
    private ObjectMapper objectMapper;
    private Canvas canvasReader;
    private ExecutorService service;
    List<Future<ShapeExecutorResponse>> shapeExecutorsResponseFutures;

    String shapeJsonToPost;
    ShapeService shapeService = new ShapeService();

    public void init() throws IOException {

        String shapesJson;
        shapesJson = (String)shapeService.get();

        objectMapper = new ObjectMapper();
        canvasReader = (Canvas) objectMapper.readValue(shapesJson, Canvas.class);

        service = Executors.newFixedThreadPool(2);

        shapeExecutorsResponseFutures = new ArrayList<Future<ShapeExecutorResponse>>();
    }

    public void process() throws ExecutionException, InterruptedException, IOException {

        ExecutorFactory executorFactory = new ExecutorFactory();

        for (Shape shape : canvasReader.getShapes()) {
            final ShapeExecutor shapeExecutor = executorFactory.getShapeExecutor(shape);
            shapeExecutorsResponseFutures.add(service.submit(new Callable<ShapeExecutorResponse>() {
                @Override
                public ShapeExecutorResponse call() {
                    return shapeExecutor.execute();
                }
            }));
        }

        for (Future<ShapeExecutorResponse> future : shapeExecutorsResponseFutures) {
            ShapeExecutorResponse shapeExecutorResponse = (ShapeExecutorResponse)future.get();
            logger.info(shapeExecutorResponse.getMessage());
            logger.info(shapeExecutorResponse.getShape());
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
