package Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtractJsonKeys {



    public static void main(String[] args) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get("src/test/java/Users/json2.txt"))).trim();
        String[] keysToExtract = {"externalId", "name", "slug", "description", "metaDescription", "metaTitle", "key"};

        extractAndProcessJsonData(jsonData, keysToExtract, "src/test/java/Users/json.txt");
    }

    /**
     * Extracts and processes JSON data based on its format (array or single object).
     *
     * @param jsonData      The JSON data to be extracted and processed.
     * @param keysToExtract The keys to extract from the JSON data.
     * @param filePath      The file path to write the extracted data.
     */
    public static void extractAndProcessJsonData(String jsonData, String[] keysToExtract, String filePath) {
        // Check if JSON data is an array
        if (jsonData.startsWith("[")) {
            // If JSON data is an array, extract keys from JSON array
            JSONArray jsonArray = new JSONArray(jsonData);
            extractKeysFromJsonArray(jsonArray, keysToExtract, filePath);
        } else {
            // If JSON data is a single object, extract keys from single JSON object
            extractKeysFromSingleJsonObject(jsonData, keysToExtract, filePath);
        }
    }

    /**
     * Extracts specified keys from a single JSON object.
     *
     * @param jsonString    The JSON string representing a single JSON object.
     * @param keysToExtract The keys to extract from the JSON object.
     * @param filePath      The file path to write the extracted data.
     */
    public static void extractKeysFromSingleJsonObject(String jsonString, String[] keysToExtract, String filePath) {
        // Parse JSON string into a JSONObject
        JSONObject jsonObj = new JSONObject(jsonString);
        // Create a new JSONObject to store extracted data
        JSONObject extractedData = new JSONObject();

        // Iterate over keys to extract
        for (String key : keysToExtract) {
            // Check if JSON object contains the key
            if (jsonObj.has(key)) {
                // Put key-value pair into extractedData JSONObject
                extractedData.put(key, jsonObj.get(key));
            }
        }

        // Write extracted data to file
        writeJSONObjectToFile(filePath, extractedData);
    }

    /**
     * Extracts specified keys from a JSON array of objects.
     *
     * @param jsonArray     The JSON array containing multiple JSON objects.
     * @param keysToExtract The keys to extract from each JSON object in the array.
     * @param filePath      The file path to write the extracted data.
     */
    public static void extractKeysFromJsonArray(JSONArray jsonArray, String[] keysToExtract, String filePath) {
        // Create a new JSONArray to store extracted data
        JSONArray extractedArray = new JSONArray();

        // Iterate over each JSON object in the array
        for (int i = 0; i < jsonArray.length(); i++) {
            // Get the current JSON object
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            // Create a new JSONObject to store extracted data for the current object
            JSONObject extractedData = new JSONObject();

            // Iterate over keys to extract
            for (String key : keysToExtract) {
                // Check if JSON object contains the key
                if (jsonObj.has(key)) {
                    // Put key-value pair into extractedData JSONObject
                    extractedData.put(key, jsonObj.get(key));
                }
            }

            // Add extracted data for the current object to the extractedArray
            extractedArray.put(extractedData);
        }

        // Write extracted data array to file
        writeJSONArrayToFile(filePath, extractedArray);
    }

    /**
     * Writes a JSONObject to a file.
     *
     * @param filePath      The file path to write the JSONObject.
     * @param extractedData The JSONObject to write to the file.
     */
    public static void writeJSONObjectToFile(String filePath, JSONObject extractedData) {
        try (FileWriter file = new FileWriter(filePath)) {
            // Write JSONObject to file with pretty printing
            file.write(extractedData.toString(4)); // Pretty print with an indent factor of 4
            // Print success message
            System.out.println("Data successfully extracted and saved in " + System.getProperty("user.dir") + "\\" + filePath);
        } catch (IOException e) {
            // Print stack trace if writing to file fails
            e.printStackTrace();
        }
    }

    /**
     * Writes a JSONArray to a file.
     *
     * @param filePath      The file path to write the JSONArray.
     * @param extractedData The JSONArray to write to the file.
     */
    public static void writeJSONArrayToFile(String filePath, JSONArray extractedData) {
        try (FileWriter file = new FileWriter(filePath)) {
            // Write JSONArray to file with pretty printing
            file.write(extractedData.toString(4)); // Pretty print with an indent factor of 4
            // Print success message
            System.out.println("Data successfully extracted and saved in " + System.getProperty("user.dir") + "\\" + filePath);
        } catch (IOException e) {
            // Print stack trace if writing to file fails
            e.printStackTrace();
        }
    }
}
