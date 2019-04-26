import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Driver {

    public static void main(String[] args) throws IOException {

        Canvas canvasWriter = new Canvas();
        canvasWriter.getShapes().add(new Circle(5.0));
        canvasWriter.getShapes().add(new Pentagon(3.0, 2.0));
        canvasWriter.getShapes().add(new Rectangle(5.0D, 5.0));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("C:\\Users\\Mesmer\\IdeaProjects\\Task1_JsonMapping\\src\\main\\java\\JsonDataWriteInto"), canvasWriter);

        Canvas canvasReader = (Canvas)objectMapper.readValue(new File("C:\\Users\\Mesmer\\IdeaProjects\\Task1_JsonMapping\\src\\main\\java\\jsonData"), Canvas.class);


        for(Shape s:canvasReader.getShapes()) {
            s.display();
            System.out.println();
        }

    }
}
