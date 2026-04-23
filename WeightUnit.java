package converter;

/**
* Represents different units of weight.
* Includes pounds, ounces, kilograms, and grams.
*/
public enum WeightUnit implements Unit {
	
	POUNDS,
	
	OUNCES,
	
	KILOGRAMS,
	
	GRAMS;
	
	public String getName() { return this.name(); }

}
