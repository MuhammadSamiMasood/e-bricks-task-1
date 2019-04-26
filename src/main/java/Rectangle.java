import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rectangle extends Shape {
    private double width;
    private double length;

    @JsonCreator
    public Rectangle(@JsonProperty("width") double w, @JsonProperty("length") double l) {
        this.width = w;
        this.length = l;
    }

    @JsonGetter
    public double area() {
        return this.width * this.length;
    }

    @JsonGetter
    public double perimeter() {
        return 2.0 * (this.width + this.length);
    }

    public void display() {
        System.out.println("Length of Rectangle: " + this.length);
        System.out.println("Width of Rectangle: " + this.width);
        System.out.println("Area of Rectangle: " + this.area());
        System.out.println("perimeter of Rectangle: " + this.perimeter());
    }
}
