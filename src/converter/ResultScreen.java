package converter;

import javax.swing.*;
import java.awt.*;

/**
 * ResultScreen is the third screen of the Unit Converter application.
 * Displays the result of the conversion and provides options to save
 * the result to a file or return to the main menu.
 *
 * @author Aaron Carpenter, Weston Polak, Dillan Winegar.
 */
public class ResultScreen extends JPanel {

    /**
     * Constructs the ResultScreen displaying the given conversion result.
     *
     * @param frame  the main application frame used for screen navigation
     * @param result the Result object containing conversion details to display
     */
    public ResultScreen(UnitConverterApp frame, Result result) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // Title
        JLabel titleLabel = new JLabel("Conversion Result", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Result label
        JLabel resultLabel = new JLabel(result.toString(), SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton saveBtn = createButton("Save to File", new Color(100, 200, 130));
        JButton backBtn = createButton("Back to Menu", new Color(200, 200, 200));

        saveBtn.addActionListener(e -> result.saveToFile(
                System.getProperty("user.home") + "/Desktop/results.txt"));
        backBtn.addActionListener(e -> frame.showMainMenu());

        buttonPanel.add(saveBtn);
        buttonPanel.add(backBtn);

        add(titleLabel,  BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a styled action button with the given label and background color.
     *
     * @param text  the button label
     * @param color the background color
     * @return a styled JButton
     */
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(140, 45));
        return button;
    }
}