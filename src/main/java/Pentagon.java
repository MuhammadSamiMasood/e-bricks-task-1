import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pentagon extends Shape {
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
        System.out.println("Apothem of the Pentagon: " + this.apothem);
        System.out.println("One side of the Pentagon: " + this.side);
        System.out.println("Area of the Pentagon: " + this.area());
        System.out.println("Perimeter of the Pentagon: " + this.perimeter());
    }
}
