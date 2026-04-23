package converter;

/**
 * Represents a weight measurement with a specific unit.
 * Extends Measurement and provides conversion methods between
 * pounds, ounces, kilograms, and grams.
 *
 * @author Aaron Carpenter, Weston Polak, Dillan Winegar
 */
public class Weight extends Measurement {

    /** The unit of this weight measurement. */
    private WeightUnit weightUnit;

    /**
     * Constructs a Weight with the specified value and unit.
     *
     * @param value      the numeric value of the weight
     * @param weightUnit the unit of the weight
     */
    public Weight(double value, WeightUnit weightUnit) {
        super(value);
        this.weightUnit = weightUnit;
    }

    /**
     * Returns the unit of this weight measurement.
     *
     * @return the WeightUnit
     */
    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    /**
     * Sets the unit of this weight measurement.
     *
     * @param weightUnit the new WeightUnit
     */
    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }

    /**
     * Converts this weight to its equivalent value in kilograms (base unit).
     * This converted base unit is used to standardize all weight measurements for conversion.
     *
     * @return the value of this weight expressed in kilograms
     */
    public double toBaseUnit() {
        switch (weightUnit) {
            case POUNDS:    return getValue() * 0.453592;
            case OUNCES:    return getValue() * 0.0283495;
            case KILOGRAMS: return getValue();
            case GRAMS:     return getValue() * 0.001;
            default:        throw new IllegalStateException("Unknown unit: " + weightUnit);
        }
    }

    /**
     * Converts this weight to pounds.
     *
     * @return the value of this weight in pounds
     */
    public double toPounds() {
        return toBaseUnit() / 0.453592;
    }

    /**
     * Converts this weight to ounces.
     *
     * @return the value of this weight in ounces
     */
    public double toOunces() {
        return toBaseUnit() / 0.0283495;
    }

    /**
     * Converts this weight to kilograms.
     *
     * @return the value of this weight  in kilograms
     */
    public double toKilograms() {
        return toBaseUnit();
    }

    /**
     * Converts this weight to grams.
     *
     * @return the value of this weight in grams
     */
    public double toGrams() {
        return toBaseUnit() / 0.001;
    }

    /**
     * Returns a string representation of this weight measurement.
     *
     * @return a formatted string such as "10.0 KILOGRAMS"
     */
    @Override
    public String toString() {
        return getValue() + " " + weightUnit;
    }
}