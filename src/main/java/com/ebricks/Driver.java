package com.ebricks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {


    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Canvas canvasReader = null;
        try {
            canvasReader = (Canvas) objectMapper.readValue(new File("C:\\Users\\Mesmer\\IdeaProjects\\Task1_JsonMapping\\src\\main\\java\\com\\ebricks\\JsonData"), Canvas.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RunnableImplementation runnableImplementation[] = new RunnableImplementation[canvasReader.getShapes().size()];

        //int i = 0;
        //for (RunnableImplementation r : runnableImplementation) {
        //    r = new RunnableImplementation(canvasReader.getShapes().get(i));
        //    i++;
        //}

        for (int j = 0; j < runnableImplementation.length; j++) {
            runnableImplementation[j] = new RunnableImplementation(canvasReader.getShapes().get(j));
        }

        ExecutorService service = Executors.newFixedThreadPool(2);

        for (RunnableImplementation r : runnableImplementation) {
            service.execute(r);
        }

        return;

    }
}
