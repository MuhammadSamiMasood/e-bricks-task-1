package com.ebricks.shape.processor;

import com.ebricks.shape.model.Canvas;
import com.ebricks.shape.executor.ShapeExecutor;
import com.ebricks.shape.model.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

        URL url = new URL("http://localhost:8080/shapeserver/Shapes");
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String input = "", inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            input += inputLine;
        }
        bufferedReader.close();


        objectMapper = new ObjectMapper();
        canvasReader = (Canvas) objectMapper.readValue(input, Canvas.class);

        service = Executors.newFixedThreadPool(2);

        futures = new ArrayList<Future>();
    }

    public void process() throws ExecutionException, InterruptedException {

        for (Shape shape : canvasReader.getShapes()) {
            ShapeExecutor shapeExecutor = new ShapeExecutor(shape);
            futures.add(service.submit(shapeExecutor));
        }

        for (Future<Shape> future : futures) {
            Shape shape = future.get();
            logger.info(shape);
        }
    }

    public void end() throws InterruptedException {

        if (service != null) {
            service.shutdown();
            service.awaitTermination(10, TimeUnit.SECONDS);
        }

    }


}
