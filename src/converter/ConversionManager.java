package converter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ConversionManager {

    /** The collection of all conversion results performed in this session. 
     * */
    private ArrayList<Result> history;

    /** The path to the file used for storing and loading results. 
     * */
    private String filename;

    /**
     * Constructs a ConversionManager with the given file path and
     * loads any previously saved results from that file.
     *
     * @param filename the path to the results file
     */
    public ConversionManager(String filename) {
        this.filename = filename;
        this.history  = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Adds a new Result to the history collection.
     *
     * @param result the Result to add
     */
    public void addResult(Result result) {
        history.add(result);
    }

    /**
     * Returns the full list of conversion results.
     *
     * @return an ArrayList of all Result objects
     */
    public ArrayList<Result> getHistory() {
        return history;
    }

    /**
     * Returns the number of results stored in the history.
     *
     * @return the size of the history list
     */
    public int getHistorySize() {
        return history.size();
    }

    /**
     * Clears all results from the in-memory history.
     * Does not affect the saved file.
     */
    public void clearHistory() {
        history.clear();
    }

    /**
     * Reads previously saved results from the file and stores them
     * as strings in the history for display purposes.
     * If the file does not exist or cannot be read, the history
     * remains empty and no error is thrown.
     */
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    history.add(parseResult(line));
                }
            }
        } catch (IOException e) {
            // File may not exist yet on first run — this is expected
            System.out.println("No existing history file found. Starting fresh.");
        }
    }

    /**
     * Attempts to parse a line from the results file into a Result object.
     * If the line cannot be parsed, returns null.
     *
     * @param line a single line read from the results file
     * @return a Result object if parsing succeeds, null otherwise
     */
    private Result parseResult(String line) {
        try {
            // Example: [2024-01-15 10:30:00] LENGTH: 5.0000 FEET -> 1.5240 METERS
            String withoutTimestamp = line.substring(line.indexOf(']') + 2).trim();
            String[] typeSplit      = withoutTimestamp.split(": ", 2);
            MeasurementType type    = MeasurementType.valueOf(typeSplit[0].trim());
            String[] parts          = typeSplit[1].split(" -> ");
            String[] fromParts      = parts[0].trim().split(" ");
            String[] toParts        = parts[1].trim().split(" ");
            double fromValue        = Double.parseDouble(fromParts[0]);
            String fromUnit         = fromParts[1];
            double toValue          = Double.parseDouble(toParts[0]);
            String toUnit           = toParts[1];
            return new Result(type, fromUnit, fromValue, toUnit, toValue);
        } catch (Exception e) {
            System.err.println("Could not parse line: " + line);
            return null;
        }
    }

    /**
     * Returns a formatted string listing all results in the history,
     * one per line. Used for displaying history on the ResultScreen.
     *
     * @return a newline-separated string of all results,
     *         or "No history yet." if the list is empty
     */
    public String getHistoryAsString() {
        if (history.isEmpty()) {
            return "No history yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (Result r : history) {
            if (r != null) {
                sb.append(r.toString()).append("\n");
            }
        }
        return sb.toString().trim();
    }
}
