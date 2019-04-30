import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnableImplementation implements Runnable {
    int threadNumber;
    private Logger logger = LogManager.getLogger(RunnableImplementation.class);

    public RunnableImplementation(int tN)
    {

        threadNumber = tN;
    }
    public void run() {
        Canvas canvasWriter = new Canvas();
        canvasWriter.getShapes().add(new Circle(5.0));
        canvasWriter.getShapes().add(new Pentagon(3.0, 2.0));
        canvasWriter.getShapes().add(new Rectangle(5.0D, 5.0));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File("C:\\Users\\Mesmer\\IdeaProjects\\Task1_JsonMapping\\src\\main\\java\\JsonDataWriteInto"), canvasWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Canvas canvasReader = null;
        try {
            canvasReader = (Canvas)objectMapper.readValue(new File("C:\\Users\\Mesmer\\IdeaProjects\\Task1_JsonMapping\\src\\main\\java\\jsonData"), Canvas.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        logger.info("Thread number: " + threadNumber);

        for(Shape s:canvasReader.getShapes()) {
            s.display();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

    }
}
