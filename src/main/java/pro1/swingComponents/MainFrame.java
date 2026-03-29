package pro1.swingComponents;
import pro1.drawingModel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private DisplayPanel displayPanel;
    private OptionsPanel optionsPanel;

    private int lastX;
    private int lastY;
    private double lastAngle;

    public MainFrame() {
        this.setTitle("PRO1 - Univerzální Kreslení");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        displayPanel = new DisplayPanel();
        optionsPanel = new OptionsPanel(this);

        this.add(displayPanel, BorderLayout.CENTER);
        this.add(optionsPanel, BorderLayout.WEST);

        displayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
                lastAngle = optionsPanel.getAngle();

                showExample();
            }
        });

        this.setVisible(true);
    }

    public void showExample() {
        displayPanel.addDrawable(
                new Arrow(lastX, lastY, lastAngle)
        );
    }

    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }
}