package app;

import javax.swing.*;
import java.awt.*;

import enums.*;
import model.*;

public class ConversionScreen extends JPanel {

    public ConversionScreen(Measurement frame, MeasurementType type) {

        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel valueLabel = new JLabel("Enter value:");
        JTextField valueField = new JTextField();

        JLabel fromLabel = new JLabel("From:");
        JComboBox<String> fromUnitBox = new JComboBox<>();

        JLabel toLabel = new JLabel("To:");
        JComboBox<String> toUnitBox = new JComboBox<>();

        JButton convertBtn = new JButton("Convert");

        switch (type) {
            case LENGTH:
                for (LengthUnit u : LengthUnit.values()) {
                    fromUnitBox.addItem(u.name());
                    toUnitBox.addItem(u.name());
                }
                break;

            case WEIGHT:
                for (WeightUnit u : WeightUnit.values()) {
                    fromUnitBox.addItem(u.name());
                    toUnitBox.addItem(u.name());
                }
                break;

            case TIME:
                for (TimeUnit u : TimeUnit.values()) {
                    fromUnitBox.addItem(u.name());
                    toUnitBox.addItem(u.name());
                }
                break;
        }

        convertBtn.addActionListener(e -> {
            double input = Double.parseDouble(valueField.getText());
            String from = (String) fromUnitBox.getSelectedItem();
            String to = (String) toUnitBox.getSelectedItem();

            double resultValue = convert(type, input, from, to);

            Result result = new Result(type, from, input, to, resultValue);
            frame.showResultScreen(result);
        });

        add(valueLabel);
        add(valueField);
        add(fromLabel);
        add(fromUnitBox);
        add(toLabel);
        add(toUnitBox);
        add(new JLabel());
        add(convertBtn);
    }

    private double convert(MeasurementType type, double value, String from, String to) {

        switch (type) {

            case LENGTH:
                LengthUnit fromL = LengthUnit.valueOf(from);
                LengthUnit toL = LengthUnit.valueOf(to);

                Length len = new Length(value, fromL);

                double baseInches = len.toInches();
                return len.fromInches(baseInches, toL);

            case WEIGHT:
                WeightUnit fromW = WeightUnit.valueOf(from);
                WeightUnit toW = WeightUnit.valueOf(to);

                Weight w = new Weight(value, fromW);

                double basePounds = w.toPounds();
                return w.fromPounds(basePounds, toW);

            case TIME:
                TimeUnit fromT = TimeUnit.valueOf(from);
                TimeUnit toT = TimeUnit.valueOf(to);

                Time t = new Time(value, fromT);

                double baseSeconds = t.toSeconds();
                return t.fromSeconds(baseSeconds, toT);
        }

        return value;
    }
}
