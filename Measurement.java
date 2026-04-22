package converter;

/**
* Abstract base class representing a generic measurement with a value.
* All specific measurement types (Length, Weight, Time) extend this class.
*/
public abstract class Measurement {
	
	private double value;

	/**
	 * @param value
	 */
	public Measurement(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Measurement [value=" + value + "]";
	}
	
	

}
