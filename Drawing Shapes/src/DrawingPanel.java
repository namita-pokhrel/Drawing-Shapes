import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JComponent {
    private ArrayList<Shape> shapes;
    private ShapeType currentShapeType;
    private Color currentColor;
    private boolean trailsEnabled;

    public DrawingPanel() {
        shapes = new ArrayList<>();
        currentShapeType = ShapeType.LINE; // Default shape
        currentColor = Color.BLACK; // Default color
        trailsEnabled = false;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                addShape(startPoint, startPoint);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (trailsEnabled) {
                    addShape(startPoint, e.getPoint());
                } else {
                    Shape lastShape = shapes.get(shapes.size() - 1);
                    lastShape.setPoint2(e.getPoint());
                }
                repaint();
            }
        };

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    private void addShape(Point start, Point end) {
        Shape shape = createShape(start, end);
        shapes.add(shape);
        repaint();
    }

    private Shape createShape(Point start, Point end) {
        switch (currentShapeType) {
            case LINE:
                return new Line(start, end, currentColor);
            case BOX:
                return new Box(start, end, currentColor);
            case OVAL:
                return new Oval(start, end, currentColor);
            default:
                return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        drawInstructions(g);
    }

    private void drawInstructions(Graphics g) {
        g.setColor(Color.BLACK);
        String instructions = "(E)rase (T)rails (L)INE (B)ox (O)val (C)olor (S)ave (R)estore";
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(instructions);
        int x = (getWidth() - textWidth) / 2;
        int y = getHeight() - 20;
        g.drawString(instructions, x, y);
    }

    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    public void toggleTrails() {
        trailsEnabled = !trailsEnabled;
    }

    public void setCurrentShapeType(ShapeType type) {
        currentShapeType = type;
    }

    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    // Getter and setter for shapes list
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }
}

