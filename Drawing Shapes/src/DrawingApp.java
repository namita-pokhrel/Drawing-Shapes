//Namita Pokhrel
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class DrawingApp extends JFrame {
    private DrawingPanel drawingPanel;

    public DrawingApp() {
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
        this.setTitle("Shape Drawing Program");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        switch (Character.toUpperCase(e.getKeyChar())) {
            case 'E':
                drawingPanel.clearShapes();
                break;
            case 'T':
                drawingPanel.toggleTrails();
                break;
            case 'L':
                drawingPanel.setCurrentShapeType(ShapeType.LINE);
                break;
            case 'B':
                drawingPanel.setCurrentShapeType(ShapeType.BOX);
                break;
            case 'O':
                drawingPanel.setCurrentShapeType(ShapeType.OVAL);
                break;
            case 'C':
                Color color = JColorChooser.showDialog(this, "Choose a color", drawingPanel.getCurrentColor());
                if (color != null) {
                    drawingPanel.setCurrentColor(color);
                }
                break;
            case 'S': // Saves drawing to a file
                saveDrawing();
                break;
            case 'R': // Restores drawing from a file
                restoreDrawing();
                break;
        }
        repaint();
    }

    // Saves drawing (list of shapes) to a file
    private void saveDrawing() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(drawingPanel.getShapes()); // Write the shapes to the file
                JOptionPane.showMessageDialog(this, "Drawing saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving drawing: " + ex.getMessage());
            }
        }
    }

    // Restores drawing (list of shapes) from a file
    private void restoreDrawing() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                @SuppressWarnings("unchecked")
                ArrayList<Shape> shapes = (ArrayList<Shape>) in.readObject(); // Read the shapes from the file
                drawingPanel.setShapes(shapes); // Set the loaded shapes in the panel
                drawingPanel.repaint();
                JOptionPane.showMessageDialog(this, "Drawing restored successfully!");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error restoring drawing: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingApp app = new DrawingApp();
            app.setVisible(true);
        });
    }
}

