package pro1.swingComponents;

import pro1.drawingModel.*;
import pro1.drawingModel.Rectangle;
import pro1.utils.ColorUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class OptionsPanel extends JPanel {
    private final MainFrame parent;

    private JSlider rS, gS, bS, angleS;
    private JComboBox<String> shapeSelect;
    private JButton btnSubmit;

    private Drawable currentDrawable;

    public OptionsPanel(MainFrame parent) {
        this.parent = parent;

        this.setPreferredSize(new Dimension(250, 0));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        add(Box.createVerticalStrut(10));

        JButton btnRandom = new JButton("Náhodná barva");
        btnRandom.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRandom.addActionListener(e -> {
           String hex = ColorUtils.randomColor();
           updateCurrentDrawableColor(hex);
           updateSlidersFromHex(hex);
        });
        add(btnRandom);

        add(Box.createVerticalStrut(20));

        add(new JLabel("R:"));
        rS = createSlider();

        add(Box.createVerticalStrut(10));
        add(new JLabel("G:"));
        gS = createSlider();

        add(Box.createVerticalStrut(10));
        add(new JLabel("B:"));
        bS = createSlider();

        add(Box.createVerticalStrut(20));

        add(new JLabel("Směr šipky:"));
        angleS = new JSlider(0, 360, 0);
        add(angleS);

        add(Box.createVerticalStrut(20));

        JCheckBox cb = new JCheckBox("Všechny šipky červené");
        cb.addActionListener(e -> {
            Arrow.forceRed = cb.isSelected();
            parent.getDisplayPanel().repaint();
        });
        add(cb);

        add(Box.createVerticalStrut(20));

        add(new JLabel("Vyber tvar pro vložení:"));
        shapeSelect = new JComboBox<>(new String[]{"Ellipse", "Rectangle", "Line", "Text"});
        shapeSelect.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        add(shapeSelect);

        add(Box.createVerticalStrut(10));

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(e -> addRandomShape());
        add(btnSubmit);

        add(Box.createVerticalStrut(20));

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> parent.getDisplayPanel().clear());
        add(btnReset);
    }

    private JSlider createSlider() {
        JSlider s = new JSlider(0, 255, 0);
        s.addChangeListener(e -> {
            String hex = getCurrentColorFromSliders();
            updateCurrentDrawableColor(hex);
        });
        add(s);
        return s;
    }

    private String getCurrentColorFromSliders() {
        return ColorUtils.colorToHex(rS.getValue(), gS.getValue(), bS.getValue());
    }

    private void updateSlidersFromHex(String hex) {
        Color c = Color.decode(hex);
        rS.setValue(c.getRed());
        gS.setValue(c.getGreen());
        bS.setValue(c.getBlue());
    }

    private void updateCurrentDrawableColor(String hex) {
        if (currentDrawable == null) {
            String selected = (String) shapeSelect.getSelectedItem();
            int x = 10; int y = 10;

            switch (selected) {
                case "Ellipse" -> currentDrawable = new Ellipse(x, y, 50, 30, hex);
                case "Rectangle" -> currentDrawable = new Rectangle(x, y, 50, 30, hex);
                case "Line" -> currentDrawable = new Line(x, y, x + 50, y, 3, hex);
                case "Text" -> currentDrawable = new Text(x, y, "Hello", hex);
            }
        } else {
            if (currentDrawable instanceof Ellipse e) e.setColor(hex);
            else if (currentDrawable instanceof Rectangle r) r.setColor(hex);
            else if (currentDrawable instanceof Line l) l.setColor(hex);
            else if (currentDrawable instanceof Text t) t.setColor(hex);
        }
        parent.getDisplayPanel().repaint();
    }

    private void addRandomShape() {
        Random rnd = new Random();
        int x = rnd.nextInt(parent.getDisplayPanel().getWidth() - 50);
        int y = rnd.nextInt(parent.getDisplayPanel().getHeight() - 50);

        String colorHex = getCurrentColorFromSliders();
        String selected = (String) shapeSelect.getSelectedItem();
        Drawable d = null;

        switch (selected) {
            case "Ellipse" -> d = new Ellipse(x, y, 50, 30, colorHex);
            case "Rectangle" -> d = new Rectangle(x, y, 50, 30, colorHex);
            case "Line" -> d = new Line(x, y, x + 50, y, 3, colorHex);
            case "Text" -> d = new Text(x, y, "Hello", colorHex);
        }

        if (d != null) {
            currentDrawable = d;
            parent.getDisplayPanel().addDrawable(d);
        }
    }

    public int getAngle() {
        return angleS.getValue();
    }
}
