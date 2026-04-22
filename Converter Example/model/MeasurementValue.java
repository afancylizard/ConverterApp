package model;

public abstract class MeasurementValue {
    protected double value;

    public MeasurementValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
