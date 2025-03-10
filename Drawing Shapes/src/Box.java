import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Concrete class representing a rectangular shape.
 */
public class Box extends Shape {

        /**
     * Construct a Box with 2 points and color.
     *
     * @param point1 the starting point of the shape
     * @param point2 the ending point of the shape
     * @param color the color of the shape
     */
    public Box(Point point1, Point point2, Color color) {
        super(point1, point2, color);
    }

    @Override
    public void draw(Graphics g) {
        // Drawing logic
        g.setColor(color);
        g.drawRect(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y),
                    Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
    }
}
