package pro1.drawingModel;

import java.awt.*;

public class Text extends XY {
    private String text;
    private String color;

    public Text(int x, int y, String text, String color) {
        super(x, y);
        this.text = text;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode(color));
        g.drawString(this.text, this.x, this.y);
    }

    public void setColor(String color) {
        this.color = color;
    }
}