import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

    private static final Logger logger = LogManager.getLogger(Driver.class);
    private static final Logger comLogger = LogManager.getLogger("com.foo.Bar");

    public static void main(String[] args) throws IOException {

       RunnableImplementation r[] = new RunnableImplementation[10];
       for(int i=0; i<r.length; i++){
           r[i] = new RunnableImplementation(i+1);
       }

        ExecutorService service = Executors.newFixedThreadPool(2);

       for(int i=0; i<10;i++)
       {
           service.execute(r[i]);
       }



    }
}
