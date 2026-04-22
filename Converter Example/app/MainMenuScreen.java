package app;

import javax.swing.*;
import java.awt.*;
import enums.MeasurementType;

public class MainMenuScreen extends JPanel {

    public MainMenuScreen(Measurement frame) {
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton lengthBtn = new JButton("Length");
        JButton weightBtn = new JButton("Weight");
        JButton timeBtn = new JButton("Time");

        lengthBtn.addActionListener(e -> frame.showConversionScreen(MeasurementType.LENGTH));
        weightBtn.addActionListener(e -> frame.showConversionScreen(MeasurementType.WEIGHT));
        timeBtn.addActionListener(e -> frame.showConversionScreen(MeasurementType.TIME));

        add(lengthBtn);
        add(weightBtn);
        add(timeBtn);
    }
}
