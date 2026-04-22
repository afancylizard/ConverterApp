package converter;

/**
 * Represents a length measurement with a specific unit.
 * Extends Measurement class and provides methods for converting between
 * inches, feet, centimeters, and meters.
 * 
 * @author Dillan W
 */
public class Length extends Measurement {
	
	private LengthUnit lengthUnit;

	/**
	 * Constructs a Length with specified value and unit of length
	 * @param value      the numeric value of length
	 * @param lengthUnit the specified unit of this measurement
	 */
	public Length(double value, LengthUnit lengthUnit) {
		super(value);
		this.lengthUnit = lengthUnit;
	}
	
	public void setLengthUnit(LengthUnit lengthUnit) {
	    this.lengthUnit = lengthUnit;
	}  
	
	/**
	 * Converts this length to its equivalent value in meters (base unit).
	 * All public conversion methods call this internally to first normalize
	 * the value to meters before converting to the target unit.
	 *
	 * @return the value of this length expressed in meters
	 */
	private double toBaseUnit() {
	    switch (lengthUnit) {
	        case INCHES:      return getValue() * 0.0254;
	        case FEET:        return getValue() * 0.3048;
	        case CENTIMETERS: return getValue() * 0.01;
	        case METERS:      return getValue();
	        default:          throw new IllegalStateException("Unknown unit: " + lengthUnit);
	    }
	}
	
	/**
	 * Converts this length to inches
	 * 
	 * 
	 * @return this length in inches
	 */
	public double toInches() {
		return toBaseUnit() / 0.0254;
	}
	
	/**
	 * Converts this length to feet
	 * 
	 * @return this length in feet.
	 */
	public double toFeet() {
		return toBaseUnit() / 0.3048;
	}
	
	/**
	 * Converts this length to centimeters.
	 * 
	 * @return this length in centimeters
	 */
	public double toCentimeters() {
		return toBaseUnit() / 0.01; 
	}
	
	/**
	 * Converts this length to meters.
	 * 
	 * @return this length in meters
	 */
	public double toMeters() {
		return toBaseUnit();
	}

	@Override
	public String toString() {
		return getValue() + " " + lengthUnit;
	}
}
