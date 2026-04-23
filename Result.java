package converter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Result extends Measurement{

	
	
	/**
	 * Represents the end result of a unit conversion.
	 * Extends Measurement class and provides methods for saving to file.
	 * 
	 * 
	 * @author Aaron Carpenter, Weston Polak, Dillan Winegar
	 */
	
	private MeasurementType measurement;
	private double fromValue;
	private Unit fromUnit; 
    private Unit toUnit;
	
    /**
	 * Constructs a Result with the specified values.
	 *
	 * @param value 	The final value after the conversion
	 * @param measurementType	The type of measurement (Length, Weight, Time)
	 * @param fromValue		The starting value that the user inputed
	 * @param fromUnit		The starting unit that the user specified
	 * @param toUnit		The ending unit that the user wants to convert to
	 */
	public Result(double value, MeasurementType measurementType, double fromValue, Unit fromUnit, Unit toUnit) {
		super(value);
		this.measurement = measurementType;
		this.fromValue = fromValue;
		this.fromUnit = fromUnit;
		this.toUnit = toUnit;
	}
	

	
	 /**
	  * Used for displaying the result to the user


		 */
	@Override
    public String toString() {
        return String.format("%.2f %s is equal to %.2f %s", 
            fromValue, fromUnit.getName(), getValue(), toUnit.getName());
    }
	
	
	
	
	
	
	  /**
	 * @return the measurement
	 */
	public MeasurementType getMeasurement() {
		return measurement;
	}



	 /**
 	 * @return the fromValue
 	 */
	 public double getFromValue() {
		 return fromValue;
	 }



	 /**
 	 * @return the fromUnit
 	 */
	 public Unit getFromUnit() {
		 return fromUnit;
	 }



	 /**
 	 * @return the toUnit
 	 */
	 public Unit getToUnit() {
		 return toUnit;
	 }



	  /**
		 * Saves the value to the file on the users computer
		 * if the file doesn't exist yet, it is created.
		 */
	public void saveToFile() {
	    String fileName = "output.csv";
	    
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
	        writer.write(this.toString());
	        writer.newLine();
	        System.out.println("Result successfully saved to " + fileName);
	    } catch (IOException e) {
	        System.err.println("Did not work, error: " + e.getMessage());
	    }
	}
	
	
	 /**
	 * This is for retreiving all of the values that are stored
	 * @return list of strings		list of result's .toString
	 */
	
	public static List<String> getStoredResults() {
		
        String fileName = "output.csv";
        
        try {

            return Files.readAllLines(Paths.get(fileName));
            
        } catch (IOException e) {

            System.out.println("Save file does not exist yetr.");
            return new ArrayList<>();
        }
	}

}
