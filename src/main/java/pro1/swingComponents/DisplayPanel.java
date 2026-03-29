package pro1.swingComponents;
import pro1.drawingModel.Drawable;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayPanel extends JPanel {
    private List<Drawable> drawables = new  ArrayList<>();

    public DisplayPanel() {
        this.setBackground(Color.WHITE);
    }

    public void addDrawable(Drawable d) {
        this.drawables.add(d);
        this.repaint();
    }

    public void clear() {
        this.drawables.clear();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Drawable d : drawables) {
            d.draw(g2);
        }
    }
}