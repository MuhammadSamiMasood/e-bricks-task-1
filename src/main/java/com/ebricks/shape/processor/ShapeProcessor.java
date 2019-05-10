package com.ebricks.shape.processor;

import com.ebricks.shape.model.Canvas;
import com.ebricks.shape.executor.ShapeExecutor;
import com.ebricks.shape.model.Circle;
import com.ebricks.shape.model.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.DocFlavor;
import java.io.*;
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
    String input = "";
    String output;

    public void init() throws IOException {

        URL url = new URL("http://localhost:8080/shapeserver/Shapes");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            input += inputLine;
        }
        bufferedReader.close();
        //httpURLConnection.disconnect();

        objectMapper = new ObjectMapper();
        canvasReader = (Canvas) objectMapper.readValue(input, Canvas.class);

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
        canvasReader.getShapes().add(randomShape);
        output = objectMapper.writeValueAsString(canvasReader);
        logger.info("\n" + output);

        URL url = new URL("http://localhost:8080/shapeserver/Shapes");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //httpURLConnection.connect();

        httpURLConnection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.writeBytes(output);
        dataOutputStream.flush();
        dataOutputStream.close();

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            logger.info(response.toString());
        } else {
            System.out.println("POST request not worked");
        }


    }

    public void end() throws InterruptedException {

        if (service != null) {
            service.shutdown();
            service.awaitTermination(10, TimeUnit.SECONDS);
        }

    }


}
