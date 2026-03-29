package pro1.drawingModel;

import java.awt.*;

public class Line extends XY {
    private int endX, endY, thickness;
    private String color;

    public Line(int x, int y, int endX, int endY, int thickness, String color) {
        super(x, y);
        this.endX = endX;
        this.endY = endY;
        this.thickness = thickness;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(this.thickness));
        g.setColor(Color.decode(this.color));
        g.drawLine(this.x, this.y, this.endX, this.endY);
    }

    public void setColor(String color) {
        this.color = color;
    }
}