import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Concrete class representing a line shape.
 */
public class Line extends Shape {

        /**
     * Constructs a Line with 2 points, and color.
     *
     * @param point1 the starting point of the shape
     * @param point2 the ending point of the shape
     * @param color the color of the shape
     */
    public Line(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }

    @Override
    public void draw(Graphics g) {
        // Drawing logic for Line
        g.setColor(color);
        g.drawLine(point1.x, point1.y, point2.x, point2.y);
    }
}
