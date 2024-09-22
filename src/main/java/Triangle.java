import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

public class Triangle extends JPanel {
    private int[] xPoints, yPoints;
    private Color color;
    private boolean visible = true;
    private double angle = 0;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
        this.xPoints = new int[]{x1, x2, x3};
        this.yPoints = new int[]{y1, y2, y3};
        this.color = color;
        setOpaque(false);
        setBounds(0, 0, CONSTANTS.WITH, CONSTANTS.HEIGHT);
        this.toString();
    }

    @Override
    public String toString() {
        return "Triangle{" +
                ", yPoints=" + Arrays.toString(yPoints) +
                ", xPoints=" + Arrays.toString(xPoints) +
                '}';
    }

    public void show(boolean isVisible) {
        this.visible = isVisible;
        repaint();
    }

    public void moveTo(int dx, int dy) {
        for (int i = 0; i < 3; i++) {
            xPoints[i] += dx;
            yPoints[i] += dy;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!visible) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(CONSTANTS.STOROKE));

        int centerX = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
        int centerY = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;

        AffineTransform old = g2.getTransform();
        g2.rotate(Math.toRadians(angle), centerX, centerY);
        g2.drawPolygon(xPoints, yPoints, 3);
        g2.setTransform(old);
    }

    public boolean isVisible() {
        return visible;
    }

    public void rotate(int angle) {
        this.angle += angle;
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public void resize(double scaleFactor) {

        int centerX = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
        int centerY = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;

        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] = (int) (centerX + (xPoints[i] - centerX) * scaleFactor);
            yPoints[i] = (int) (centerY + (yPoints[i] - centerY) * scaleFactor);
        }
        repaint();
    }
}
