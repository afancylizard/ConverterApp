package model;

import enums.MeasurementType;
import java.io.FileWriter;
import java.io.IOException;

public class Result {

    private MeasurementType type;
    private String fromUnit;
    private double fromValue;
    private String toUnit;
    private double resultValue;

    public Result(MeasurementType type, String fromUnit, double fromValue, String toUnit, double resultValue) {
        this.type = type;
        this.fromUnit = fromUnit;
        this.fromValue = fromValue;
        this.toUnit = toUnit;
        this.resultValue = resultValue;
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    @Override
    public String toString() {
        return type + ": " + fromValue + " " + fromUnit + " → " +
               resultValue + " " + toUnit;
    }
}
