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


        ObjectMapper objectMapper = new ObjectMapper();

        Canvas canvasReader = null;
        try {
            canvasReader = (Canvas)objectMapper.readValue(new File("C:\\Users\\Mesmer\\IdeaProjects\\Task1_JsonMapping\\src\\main\\java\\jsonData"), Canvas.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
