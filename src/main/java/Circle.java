import javax.swing.*;
import java.awt.*;

public class Circle extends JComponent {
    private int x, y, r;
    private Color color;
    private boolean visible = true;

    public Circle(int x, int y, int r, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        setBounds(x, y, CONSTANTS.WITH, CONSTANTS.HEIGHT);
        System.out.println(this.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!visible) return;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(CONSTANTS.STOROKE));
        g2.setColor(color);
        g2.drawOval(x - r / 2, y - r / 2, r, r);
    }

    public void moveTo(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public boolean isVisible() {
        return visible;
    }

    public void show(boolean isVisible) {
        this.visible = isVisible;
        repaint();
    }

    public void setRadius(int newR){
        this.r = newR;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                ", y=" + y +
                ", x=" + x +
                '}';
    }
}