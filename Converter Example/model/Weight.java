package model;

import enums.WeightUnit;

public class Weight extends MeasurementValue {

    private WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        super(value);
        this.unit = unit;
    }

    public double toPounds() {
        switch (unit) {
            case POUNDS: return value;
            case OUNCES: return value / 16;
            case KILOGRAMS: return value * 2.20462;
            case GRAMS: return value / 453.592;
        }
        return value;
    }

    public double fromPounds(double pounds, WeightUnit target) {
        switch (target) {
            case POUNDS: return pounds;
            case OUNCES: return pounds * 16;
            case KILOGRAMS: return pounds / 2.20462;
            case GRAMS: return (pounds / 2.20462) * 1000;
        }
        return pounds;
    }
}
