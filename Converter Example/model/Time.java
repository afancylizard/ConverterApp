package model;

import enums.TimeUnit;

public class Time extends MeasurementValue {

    private TimeUnit unit;

    public Time(double value, TimeUnit unit) {
        super(value);
        this.unit = unit;
    }

    public double toSeconds() {
        switch (unit) {
            case SECONDS: return value;
            case MINUTES: return value * 60;
            case HOURS: return value * 3600;
            case DAYS: return value * 86400;
        }
        return value;
    }

    public double fromSeconds(double seconds, TimeUnit target) {
        switch (target) {
            case SECONDS: return seconds;
            case MINUTES: return seconds / 60;
            case HOURS: return seconds / 3600;
            case DAYS: return seconds / 86400;
        }
        return seconds;
    }
}
