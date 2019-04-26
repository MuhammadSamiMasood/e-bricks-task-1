import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Circle extends Shape {
    private final double PI = 3.1416;
    private double radius;

    @JsonCreator
    public Circle(@JsonProperty("radius") double r) {
        this.radius = r;
    }

    @JsonGetter
    public double area() {
        return PI * this.radius * this.radius;
    }

    @JsonGetter
    public double perimeter() {
        return 2 * PI * this.radius;
    }

    public void display() {
        System.out.println("Radius of Circle: " + this.radius);
        System.out.println("Area of Circle: " + this.area());
        System.out.println("Circumference of Circle: " + this.perimeter());
    }
}
