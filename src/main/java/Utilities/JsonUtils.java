package Utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import updatedUtilities.ExcelDataDrivenUpdated;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Retrieves JSON data from a file and returns it as a Map.
     *
     * @param jsonFileName the name of the JSON file
     * @return a Map containing JSON data
     * @throws IOException if there's an error reading the JSON file
     */
    public static Map<String, Object> getJsonDataAsMap(String jsonFileName) throws IOException {
        String completeJsonFilePath = System.getProperty("user.dir") + "/src/test/resources/" + jsonFileName;
        return objectMapper.readValue(new File(completeJsonFilePath), new TypeReference<>() {});
    }

    /**
     * Parses JSON object string into a Map of headers.
     *
     * @param jsonObjectString the JSON object string
     * @return a Map representing the JSON object
     */
    public static Map<String, String> parseJsonObject(String jsonObjectString) {
        Map<String, String> jsonObjectMap = new HashMap<>();

        // Remove leading and trailing braces from the JSON string
        jsonObjectString = jsonObjectString.replace("{", "").replace("}", "");

        // Split the JSON string by commas to separate key-value pairs
        String[] keyValues = jsonObjectString.split(",");

        // Iterate through each key-value pair
        for (String keyValue : keyValues) {
            // Split the pair by colon to get the key and value
            String[] pair = keyValue.split(":");
            if (pair.length == 2) {
                // Trim and remove surrounding quotes from the key and value
                String key = pair[0].trim().replaceAll("^\"|\"$", "");
                String value = pair[1].trim().replaceAll("^\"|\"$", "");
                // Put the key-value pair into the map
                jsonObjectMap.put(key, value);
            }
        }

        return jsonObjectMap;
    }

    /**
     * Retrieves JSON data from an Excel file and returns it as an iterator of Maps.
     *
     * @param cellName the name of the cell containing JSON data in Excel
     * @return an Iterator of Maps containing JSON data
     * @throws IOException if there's an error reading the Excel file
     */
    public static Iterator<Map<String, String>> getJsonData(String cellName) throws IOException {
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelDataDrivenUpdated.loadDSheetData("src/test/resources/TestData/RestAssured.xlsx", "Sheet2");
        List<Map<String, String>> headersList = new ArrayList<>();

        for (Map<String, String> data : excelDataAsListOfMap) {
            String headersJson = data.get(cellName);
            // Parse JSON headers into a map
            Map<String, String> headersMap = objectMapper.readValue(headersJson, new TypeReference<Map<String, String>>() {});
            headersList.add(headersMap);
        }
        return headersList.iterator();
    }
}
