import javax.swing.*;
import java.awt.*;

public class Rectangle extends JComponent {
    private int x, y, width, height;
    private Color color;
    private boolean visible = true;

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        setBounds(x, y, CONSTANTS.WITH, CONSTANTS.HEIGHT);
        System.out.println(this.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!visible) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(CONSTANTS.STOROKE));
        g2.drawRect(x, y, width, height);
    }

    public void moveTo(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        setBounds(x, y,  CONSTANTS.WITH, CONSTANTS.HEIGHT);
        repaint();
    }
    public boolean isVisible() {
        return visible;
    }
    public void show(boolean isVisible) {
        this.visible = isVisible;
        repaint();
    }

    public void resize(int newWidth, int newHeight) {
        this.width = newWidth;
        this.height = newHeight;
        setBounds(x, y,  CONSTANTS.WITH, CONSTANTS.HEIGHT);
        repaint();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
