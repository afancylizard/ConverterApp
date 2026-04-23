package converter;

import javax.swing.*;
import java.awt.*;

/**
 * UnitConverterApp is the main application window for the Unit Converter.
 * It extends JFrame and manages navigation between the three screens:
 * MainMenuScreen, ConversionScreen, and ResultScreen.
 *
 * @author Aaron Carpenter, Weston Polak, Dillan Winegar.
 */
public class UnitConverterApp extends JFrame {

    /** The currently displayed panel. */
    private JPanel currentPanel;

    /**
     * Constructs the main application window and displays the home screen.
     */
    public UnitConverterApp() {
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        showMainMenu();
    }

    /**
     * Navigates to the main menu screen.
     * Called on startup and when the user clicks Back from any screen.
     */
    public void showMainMenu() {
        swapPanel(new MainMenuScreen(this));
    }

    /**
     * Navigates to the conversion screen for the given measurement type.
     * Called by MainMenuScreen when the user selects a category.
     *
     * @param type the MeasurementType the user selected
     */
    public void showConversionScreen(MeasurementType type) {
        swapPanel(new ConversionScreen(this, type));
    }

    /**
     * Navigates to the result screen and displays the given Result.
     * Called by ConversionScreen after a successful conversion.
     *
     * @param result the Result object containing conversion details
     */
    public void showResultScreen(Result result) {
        swapPanel(new ResultScreen(this, result));
    }

    /**
     * Replaces the currently displayed panel with a new one.
     * Removes the old panel, adds the new one, and refreshes the frame.
     *
     * @param newPanel the panel to display
     */
    private void swapPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = newPanel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Application entry point. Creates and displays the main window
     * on the Swing event dispatch thread.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UnitConverterApp app = new UnitConverterApp();
            app.setVisible(true);
        });
    }
}