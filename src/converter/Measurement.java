package converter;

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
