import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Concrete class representing an oval shape.
 */
public class Oval extends Shape {

        /**
     * Constructs an Oval with 2 points and color.
     *
     * @param point1 the starting point of the shape
     * @param point2 the ending point of the shape
     * @param color the color of the shape
     */
    public Oval(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }

    @Override
    public void draw(Graphics g) {
        // Drawing logic for Oval 
        g.setColor(color);
        g.drawOval(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y),
                    Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
    }
}
