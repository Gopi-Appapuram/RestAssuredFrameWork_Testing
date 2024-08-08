package Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.octomix.josson.Josson;
import org.apache.poi.hpsf.Date;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class JSONCSVConverter_1 {

    public static void main(String[] args) throws JsonProcessingException {
        // Define the input JSON file path
        String jsonFilePath = "src/test/resources/TestData/Sample.json";
        // Define the output CSV file path
        String csvFilePath = "src/test/resources/TestData/Sample1.csv";
        LocalTime startTime = new LocalTime();
        System.out.println(startTime);

        // Convert JSON to CSV and write to file
        convertJsonToCsv(jsonFilePath, csvFilePath);

        LocalTime endTime = new LocalTime();
        System.out.println(endTime);

    }

    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) {
        try {
            // Load JSON from file
            String jsonString = loadJsonFromFile(jsonFilePath).trim();

            jsonString = jsonString.replace("\"value\":\"", "\"value\":[\"")
                    .replace("\"},{\"name\":", "\"]},{\"name\":")
                    .replace("\"value\":{\"", "\"value\":[{\"")
                    .replace("}},{\"name\"", "}]},{\"name\"");
                    //.replace("\"value\":[{", "\"value\":[\"{")
//                    .replace("\"value\":[\"", "\"value\":[{\"")
//                    .replace("\"]},{\"name\":", "\"}]},{\"name\":");

            System.out.println(jsonString);

            if (jsonString != null) {

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonArray = objectMapper.readTree(jsonString);

                Set<String> allKeys = new LinkedHashSet<>();
                for (JsonNode jsonObject : jsonArray) {
                    Josson object = Josson.create(Josson.readJsonNode(jsonObject));
                    JsonNode flatObject = object.getNode("flatten('.')");

                    Iterator<String> fieldNames = flatObject.fieldNames();
                    while (fieldNames.hasNext()) {
                        allKeys.add(fieldNames.next());
                    }
                }

                try (FileWriter writer = new FileWriter(csvFilePath)) {
                    writer.append(String.join("||", allKeys));
                    writer.append("\n");

                    for (JsonNode jsonObject : jsonArray) {
                        Josson object = Josson.create(Josson.readJsonNode(jsonObject));
                        JsonNode flatObject = object.getNode("flatten('.')");

                        StringBuilder values = new StringBuilder();
                        for (String key : allKeys) {
                            if (flatObject.has(key)) {
                                String value = flatObject.get(key).asText().trim();
                                //To get all the details with-out skipping
                                values.append("\"").append(value.replace("\"", "\"\"")).append("\"");
//                                //To Skip the values
//                                if (value.equals("productAttributeList") || value.contains("\":\"")) {
//                                    values.append("skipped");
//                                } else {
//                                    values.append("\"").append(value.replace("\"", "\"\"")).append("\"");
//                                }
                            }
                            values.append("||");
                        }
                        values.setLength(values.length() - 1);
                        writer.append(values.toString());
                        writer.append("\n");
                    }
                }
            } else {
                System.err.println("Failed to load JSON from file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadJsonFromFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
