package converter;

import javax.swing.*;
import java.awt.*;

/**
 * MainMenuScreen is the home screen of the Unit Converter application.
 * Prompts the user to select a measurement category to convert.
 *
 * @author Aaron Carpenter, Weston Polak, Dillan Winegar
 */
public class MainMenuScreen extends JPanel {

    /**
     * Constructs the MainMenuScreen with three category buttons.
     *
     * @param frame the main application frame used for screen navigation
     */
    public MainMenuScreen(UnitConverterApp frame) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // Title
        JLabel titleLabel = new JLabel("Unit Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Subtitle
        JLabel subtitleLabel = new JLabel("Select a category to get started", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);

        // Header panel
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 6));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 16));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));

        JButton lengthBtn = createButton("Length");
        JButton weightBtn = createButton("Weight");
        JButton timeBtn   = createButton("Time");

        lengthBtn.addActionListener(e -> frame.showConversionScreen(MeasurementType.LENGTH));
        weightBtn.addActionListener(e -> frame.showConversionScreen(MeasurementType.WEIGHT));
        timeBtn.addActionListener(e -> frame.showConversionScreen(MeasurementType.TIME));

        buttonPanel.add(lengthBtn);
        buttonPanel.add(weightBtn);
        buttonPanel.add(timeBtn);

        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a styled category button with a hover effect.
     *
     * @param text the button label
     * @return a styled JButton
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(240, 240, 240));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(300, 70));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(210, 230, 255));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(240, 240, 240));
            }
        });

        return button;
    }
}