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
	
	
	public Result(double value, MeasurementType measurementType, double fromValue, Unit fromUnit, Unit toUnit) {
		super(value);
		this.measurement = measurementType;
		this.fromValue = fromValue;
		this.fromUnit = fromUnit;
		this.toUnit = toUnit;
	}
	

	
	
	@Override
    public String toString() {
        return String.format("%.2f %s is equal to %.2f %s", 
            fromValue, fromUnit.getName(), getValue(), toUnit.getName());
    }
	
	
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
