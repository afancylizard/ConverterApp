package converter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the end result of a unit conversion.
 * Stores the measurement type, the from and to units, the original value,
 * the converted value, and a timestamp. Provides a method to save to file.
 *
 */
public class Result {

    /** The category of measurement (LENGTH, WEIGHT, or TIME). */
    private MeasurementType type;

    /** The name of the unit being converted from. */
    private String fromUnit;

    /** The original value entered by the user. */
    private double fromValue;

    /** The name of the unit being converted to. */
    private String toUnit;

    /** The converted result value. */
    private double toValue;

    /** The date and time when this conversion was performed. */
    private String timestamp;

    /** Formatter for the timestamp. */
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a Result with the given conversion details.
     * Automatically records the current date and time as the timestamp.
     *
     * @param type      the category of measurement
     * @param fromUnit  the name of the unit converted from
     * @param fromValue the original value entered by the user
     * @param toUnit    the name of the unit converted to
     * @param toValue   the converted result value
     */
    public Result(MeasurementType type, String fromUnit, double fromValue,
                  String toUnit, double toValue) {
        this.type      = type;
        this.fromUnit  = fromUnit;
        this.fromValue = fromValue;
        this.toUnit    = toUnit;
        this.toValue   = toValue;
        this.timestamp = LocalDateTime.now().format(FORMATTER);
    }

    /**
     * Returns the measurement type of this result.
     *
     * @return the MeasurementType
     */
    public MeasurementType getType() { return type; }

    /**
     * Returns the unit converted from.
     *
     * @return the from unit name
     */
    public String getFromUnit() { return fromUnit; }

    /**
     * Returns the original value entered by the user.
     *
     * @return the from value
     */
    public double getFromValue() { return fromValue; }

    /**
     * Returns the unit converted to.
     *
     * @return the to unit name
     */
    public String getToUnit() { return toUnit; }

    /**
     * Returns the converted result value.
     *
     * @return the to value
     */
    public double getToValue() { return toValue; }

    /**
     * Returns the timestamp of when this conversion was performed.
     *
     * @return the timestamp string
     */
    public String getTimestamp() { return timestamp; }

    /**
     * Appends this result to the save text file.
     * Each result written in following format:
     * [timestamp] TYPE: fromValue FROMUNIT -> toValue TOUNIT
     * Creates the file if it does not already exist.
     *
     * @param filename the path to the file to save to
     */
    public void saveToFile(String filename) {
        try {
            // Temporary - shows where Java is looking
            System.out.println("Saving to: " + new java.io.File(filename).getAbsolutePath());
            
            java.io.File file = new java.io.File(filename);
            file.getParentFile().mkdirs();

            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                writer.println(toFileString());
            }
        } catch (IOException e) {
            System.err.println("Error saving result to file: " + e.getMessage());
        }
    }

    /**
     * Formats this result as a single line string for file storage.
     *
     * @return a formatted string 
     *        
     */
    private String toFileString() {
        return String.format("[%s] %s: %.4f %s -> %.4f %s",
                timestamp, type, fromValue, fromUnit, toValue, toUnit);
    }

    /**
     * Returns a string representation of this result
     * for display on the ResultScreen.
     *
     * @return a formatted string such as "5.0 FEET = 1.524 METERS"
     */
    @Override
    public String toString() {
        return String.format("%.4f %s  =  %.4f %s", fromValue, fromUnit, toValue, toUnit);
    }
}