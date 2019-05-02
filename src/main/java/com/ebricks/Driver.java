package com.ebricks;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.paint.LinearGradient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

    public static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Canvas canvasReader = null;
        try {
            canvasReader = (Canvas) objectMapper.readValue(new File("src\\main\\java\\com\\ebricks\\JsonData"), Canvas.class);
        } catch (IOException e) {
            logger.error(e);
        }

        ExecutorService service = Executors.newFixedThreadPool(2);

        for(Shape shape:canvasReader.getShapes()){
            RunnableImplementation runnableImplementation = new RunnableImplementation(shape);
            service.execute(runnableImplementation);
        }

        return;

    }
}
