package app;

import javax.swing.*;
import java.awt.*;

public class Measurement extends JFrame {

    private JPanel currentPanel;

    public Measurement() {
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        showMainMenu();
    }

    public void showMainMenu() {
        swapPanel(new MainMenuScreen(this));
    }

    public void showConversionScreen(enums.MeasurementType type) {
        swapPanel(new ConversionScreen(this, type));
    }

    public void showResultScreen(model.Result result) {
        swapPanel(new ResultScreen(this, result));
    }

    private void swapPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = newPanel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Measurement app = new Measurement();
            app.setVisible(true);
        });
    }
}
