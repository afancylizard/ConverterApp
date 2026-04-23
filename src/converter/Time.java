package converter;

/**
 * Represents a time measurement with a specific unit.
 * Extends Measurement and provides conversion methods between
 * seconds, minutes, hours, and days.
 *
 * @author Aaron Carpenter, Weston Polak, Dillan Winegar.
 */
public class Time extends Measurement {

    /** The unit of this time measurement. */
    private TimeUnit timeUnit;

    /**
     * Constructs a Time with the specified value and unit.
     *
     * @param value    the numeric value of the time
     * @param timeUnit the unit of the time
     */
    public Time(double value, TimeUnit timeUnit) {
        super(value);
        this.timeUnit = timeUnit;
    }

    /**
     * Returns the unit of this time measurement.
     *
     * @return the TimeUnit
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Sets the unit of this time measurement.
     *
     * @param timeUnit the new TimeUnit
     */
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * Converts this time to its equivalent value in seconds (base unit).
     * This converted base unit is used to standardize all time measurements for conversion.
     *
     * @return the value of this time expressed in seconds
     */
    public double toBaseUnit() {
        switch (timeUnit) {
            case SECONDS: return getValue();
            case MINUTES: return getValue() * 60;
            case HOURS:   return getValue() * 3600;
            case DAYS:    return getValue() * 86400;
            default:      throw new IllegalStateException("Unknown unit: " + timeUnit);
        }
    }

    /**
     * Converts this time to seconds.
     * Returns the base unit value directly as seconds is the base unit for time.
     *
     * @return the value of this time expressed in seconds
     */
    public double toSeconds() {
        return toBaseUnit();
    }

    /**
     * Converts this time to minutes.
     *
     * @return the value of this time in minutes
     */
    public double toMinutes() {
        return toBaseUnit() / 60;
    }

    /**
     * Converts this time to hours.
     *
     * @return the value of this time in hours
     */
    public double toHours() {
        return toBaseUnit() / 3600;
    }

    /**
     * Converts this time to days.
     *
     * @return the value of this time in days
     */
    public double toDays() {
        return toBaseUnit() / 86400;
    }

    /**
     * Returns a string representation of this time measurement.
     *
     * @return a formatted string such as "90.0 MINUTES"
     */
    @Override
    public String toString() {
        return getValue() + " " + timeUnit;
    }
}