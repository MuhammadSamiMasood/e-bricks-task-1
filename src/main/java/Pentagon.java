import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pentagon extends Shape {
    private Logger logger = LogManager.getLogger(Pentagon.class);
    private double apothem;
    private double side;

    @JsonCreator
    public Pentagon(@JsonProperty("apothem") double a, @JsonProperty("side") double s) {
        this.apothem = a;
        this.side = s;
    }

    @JsonGetter
    public double area() {
        return 5.0 * 0.5 * this.apothem * this.side;
    }

    @JsonGetter
    public double perimeter() {
        return 5.0 * this.side;
    }

    public void display() {
        logger.debug("Apothem of the Pentagon: " + this.apothem);
        logger.info("One side of the Pentagon: " + this.side);
        logger.info("Area of the Pentagon: " + this.area());
        logger.info("Perimeter of the Pentagon: " + this.perimeter());
    }
}
