package pro1.drawingModel;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Arrow extends XY {
    private double angle;

    public static boolean forceRed = false;

    public Arrow(int x, int y, double angle) {
        super(x, y);
        this.angle = angle;
    }

    @Override
    public void draw(Graphics2D g) {
        if (forceRed) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }

        g.setStroke(new BasicStroke(2));

        AffineTransform old = g.getTransform();
        g.translate(x, y);
        g.rotate(Math.toRadians(angle));

        g.drawLine(0, 0, -25, -10);
        g.drawLine(0, 0, -25, 10);
        g.drawLine(0, 0, -40, 0);

        g.setTransform(old);
    }
}
