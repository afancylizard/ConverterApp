package converter;

import javax.swing.*;
import java.awt.*;

/**
 * ConversionScreen is the second screen of the Unit Converter app.
 * Displays a value input prompt as well as two drop-down box to select units the user will be 
 * converting from and converting to.
 *
 */
public class ConversionScreen extends JPanel {

	/**
	 * Constructs the ConversionScreen for the given measurement type.
	 *
	 * @param frame the main application frame used for screen navigation
	 * @param type  the MeasurementType selected on MainMenuScreen
	 */
	public ConversionScreen(UnitConverterApp frame, MeasurementType type) {
		setLayout(new BorderLayout(10, 10));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

		// Title
		JLabel titleLabel = new JLabel(formatTitle(type), SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		// Form panel
		JPanel formPanel = new JPanel(new GridLayout(3, 1, 0, 16));
		formPanel.setBackground(Color.WHITE);

		JLabel valueLabel = new JLabel("Enter value to convert:");
		valueLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JTextField valueField = new JTextField();
		valueField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		valueField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(5, 10, 5, 10)));

		JLabel fromLabel = new JLabel("Convert from:");
		fromLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JComboBox<String> fromUnitBox = new JComboBox<>();

		JLabel toLabel = new JLabel("Convert to:");
		toLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JComboBox<String> toUnitBox = new JComboBox<>();

		// Populate dropdowns directly from enum values
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

		// Default to-unit to second option to avoid same-unit default
		if (toUnitBox.getItemCount() > 1) {
			toUnitBox.setSelectedIndex(1);
		}

		formPanel.add(createLabeledComponent(valueLabel, valueField));
		formPanel.add(createLabeledComponent(fromLabel, fromUnitBox));
		formPanel.add(createLabeledComponent(toLabel, toUnitBox));

		// Button panel
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 16, 0));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		JButton backBtn = createButton("Back", new Color(200, 200, 200));
		JButton convertBtn = createButton("Convert", new Color(100, 160, 255));

		backBtn.addActionListener(e -> frame.showMainMenu());

		convertBtn.addActionListener(e -> {
			String input = valueField.getText().trim();

			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a value to convert.", "Input Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			double value;
			try {
				value = Double.parseDouble(input);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String from = (String) fromUnitBox.getSelectedItem();
			String to = (String) toUnitBox.getSelectedItem();
			double resultValue = convert(type, value, from, to);
			Result result = new Result(type, from, value, to, resultValue);

			frame.showResultScreen(result);
		});

		buttonPanel.add(backBtn);
		buttonPanel.add(convertBtn);

		add(titleLabel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Performs the unit conversion by delegating to the appropriate measurement
	 * class based on the MeasurementType.
	 *
	 * @param type  the type of measurement
	 * @param value the numeric value to convert
	 * @param from  the name of the unit to convert from
	 * @param to    the name of the unit to convert to
	 * @return the converted numeric value
	 */
	private double convert(MeasurementType type, double value, String from, String to) {
		switch (type) {
		case LENGTH:
			Length len = new Length(value, LengthUnit.valueOf(from));
			switch (LengthUnit.valueOf(to)) {
			case INCHES:
				return len.toInches();
			case FEET:
				return len.toFeet();
			case CENTIMETERS:
				return len.toCentimeters();
			case METERS:
				return len.toMeters();
			}
		case WEIGHT:
			Weight w = new Weight(value, WeightUnit.valueOf(from));
			switch (WeightUnit.valueOf(to)) {
			case POUNDS:
				return w.toPounds();
			case OUNCES:
				return w.toOunces();
			case KILOGRAMS:
				return w.toKilograms();
			case GRAMS:
				return w.toGrams();
			}
		case TIME:
			Time t = new Time(value, TimeUnit.valueOf(from));
			switch (TimeUnit.valueOf(to)) {
			case SECONDS:
				return t.toSeconds();
			case MINUTES:
				return t.toMinutes();
			case HOURS:
				return t.toHours();
			case DAYS:
				return t.toDays();
			}
		default:
			return value;
		}
	}

	/**
	 * Formats the MeasurementType into a display title string.
	 *
	 * @param type the MeasurementType to format
	 * @return a capitalized title such as "Length Converter"
	 */
	private String formatTitle(MeasurementType type) {
		String name = type.toString();
		return name.charAt(0) + name.substring(1).toLowerCase() + " Converter";
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
		button.setPreferredSize(new Dimension(120, 45));
		return button;
	}

	/**
	 * Creates a small panel pairing a label above a component.
	 *
	 * @param label     the descriptive label
	 * @param component the input component
	 * @return a JPanel containing the label and component stacked vertically
	 */
	private JPanel createLabeledComponent(JLabel label, JComponent component) {
		JPanel panel = new JPanel(new BorderLayout(0, 4));
		panel.setBackground(Color.WHITE);
		panel.add(label, BorderLayout.NORTH);
		panel.add(component, BorderLayout.CENTER);
		return panel;
	}
}