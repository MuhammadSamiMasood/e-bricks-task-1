package com.ebricks.task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Driver {

    public static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();

        Canvas canvasReader = null;
        try {
            canvasReader = (Canvas) objectMapper.readValue(new File("src\\main\\java\\com\\ebricks\\task1\\JsonData"), Canvas.class);
        } catch (IOException e) {
            logger.error(e);
        }

        ExecutorService service = Executors.newFixedThreadPool(2);

        List<Future> futures = new ArrayList<Future>();
        for(Shape shape:canvasReader.getShapes()){
            CallableImplementation callableImplementation = new CallableImplementation(shape);
            futures.add(service.submit(callableImplementation));
        }

        //----------------------

        for(Future<String> future:futures){
            String string = future.get();

            logger.info(string);
        }

        return;

    }
}
