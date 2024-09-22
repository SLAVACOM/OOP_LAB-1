import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

class Line extends JComponent {
    private int x1, x2, y1, y2;
    private Color color;
    private boolean visible = true;
    private double angle = 0; // Угол для поворота

    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        setBounds(0, 0, CONSTANTS.WITH, CONSTANTS.HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!visible) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(CONSTANTS.STOROKE));

        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;

        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(angle), centerX, centerY);
        g2.drawLine(x1, y1, x2, y2);
        g2.setTransform(old);
    }

    public void moveTo(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;
        this.x2 += dx;
        this.y2 += dy;
        repaint();
    }

    public void show(boolean isVisible) {
        this.visible = isVisible;
        repaint();
    }
    public boolean isVisible() {
        return visible;
    }

    public void rotate(int angle){
        this.angle += angle;
        repaint();
    }

}
