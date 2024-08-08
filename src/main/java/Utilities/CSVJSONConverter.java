package Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octomix.josson.Josson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVJSONConverter {

    public static void main(String[] args) throws JsonProcessingException {
        // Define the input JSON file path
        String jsonFilePath = "src/test/resources/TestData/Sample.json";
        // Define the output CSV file path
        String csvFilePath = "src/test/resources/TestData/Sample.csv";

        // Convert JSON to CSV and write to file
        convertJsonToCsv(jsonFilePath, csvFilePath);
    }

    private static void convertJsonToCsv(String jsonFilePath, String csvFilePath) {
        try {
            // Load JSON from file
            String jsonString = loadJsonFromFile(jsonFilePath);

            if (jsonString != null) {
                // Create Josson object from JSON string
                Josson object = Josson.fromJsonString(jsonString);

                // Get keys and values in CSV format
                String keys = object.getString("flatten('.','[%d]').keys().csv()");
                String values = object.getString("flatten('.','[%d]').csv()");

                // Write keys and values to the CSV file
                writeCsvToFile(csvFilePath, keys, values);
            } else {
                System.err.println("Failed to load JSON from file.");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static String loadJsonFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeCsvToFile(String filePath, String keys, String values) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(keys);
            writer.append("\n");
            writer.append(values);
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
