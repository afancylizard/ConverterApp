package converter;

/**
* Represents different units of length.
* Includes inches, feet, centimeters, and meters.
*/
public enum LengthUnit implements Unit{
	
	INCHES,
	
	FEET,
	
	CENTIMETERS,
	
	METERS;
	
	public String getName() { return this.name(); }

}
