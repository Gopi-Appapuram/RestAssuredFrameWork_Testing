package Users;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.json.JSONObject;
import org.json.JSONArray;

public class JsonExtractor {

    static String jsonData;

    public static void main(String[] args) {
        try (FileReader file = new FileReader("src/test/java/Users/json2.txt")) {
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = file.read()) != -1) {
                sb.append((char) i);
            }
            jsonData = sb.toString();
            System.out.println(jsonData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] keysToExtract = {"externalId", "name","place", "description", "metaDescription", "metaTitle", "key"};
        extractJsonKeys(jsonData, keysToExtract, "src/test/java/Users/json.txt");
    }

    public static void extractJsonKeys(String jsonString, String[] keysToExtract, String filePath) {
        JSONObject extractedData = new JSONObject();
        extractKeysRecursively(new JSONObject(jsonString), keysToExtract, extractedData);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(extractedData.toString(4)); // Pretty print with an indent factor of 4
            System.out.println("Data successfully extracted and saved in " + System.getProperty("user.dir") + "\\" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractKeysRecursively(JSONObject jsonObj, String[] keysToExtract, JSONObject extractedData) {
        for (String key : keysToExtract) {
            if (jsonObj.has(key)) {
                extractedData.put(key, jsonObj.get(key));
            }
        }

        for (String key : jsonObj.keySet()) {
            Object value = jsonObj.get(key);
            if (value instanceof JSONObject) {
                extractKeysRecursively((JSONObject) value, keysToExtract, extractedData);
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object arrayElement = jsonArray.get(i);
                    if (arrayElement instanceof JSONObject) {
                        extractKeysRecursively((JSONObject) arrayElement, keysToExtract, extractedData);
                    }
                }
            }
        }
    }


    public static void extractNestedJson(JSONObject jsonObj, String[] keysToExtract){

    }


}
