package model;

import enums.LengthUnit;

public class Length extends MeasurementValue {

    private LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        super(value);
        this.unit = unit;
    }

    public double toInches() {
        switch (unit) {
            case INCHES: return value;
            case FEET: return value * 12;
            case CENTIMETERS: return value / 2.54;
            case METERS: return value * 39.3701;
        }
        return value;
    }

    public double fromInches(double inches, LengthUnit target) {
        switch (target) {
            case INCHES: return inches;
            case FEET: return inches / 12;
            case CENTIMETERS: return inches * 2.54;
            case METERS: return inches * 0.0254;
        }
        return inches;
    }
}
