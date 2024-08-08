package Utilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class CsvToJsonConverter {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String csvFilePath = "src/test/resources/TestData/MOCK_DATA.csv"; // Change to your CSV file path
        String outputDir = "src/test/resources/TestData/MOCK_DATA"; // Change to your output directory path
        String jsonFilePath = "src/test/resources/TestData/MOCK_DATA.json"; // Change to your CSV file path
        String outputDircsv = "src/test/resources/TestData/MOCK_DATA.csv"; // Change to your output directory path
        //CsvToJson(csvFilePath, outputDir);


        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        double seconds = (double) totalTime/1_000_000_000;
        System.out.println("The execution in seconds: "+seconds);
        JsonToCsv(jsonFilePath, outputDircsv);
    }

    public static void CsvToJson(String csvFilePath, String outputDir) {
        ArrayList<StringBuilder> jsonArray = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                System.out.println("Empty CSV file.");
                return;
            }

            String[] headers = headerLine.split(",");
            String line;
            int rowNumber = 1;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                StringBuilder jsonBuilder = new StringBuilder();
                jsonBuilder.append("{");

                for (int i = 0; i < headers.length; i++) {
                    jsonBuilder.append("\"").append(headers[i]).append("\": ");
                    jsonBuilder.append("\"").append(values[i]).append("\"");
                    if (i < headers.length - 1) {
                        jsonBuilder.append(", ");
                    }
                }

                jsonBuilder.append("}");
                //System.out.println(jsonBuilder);
                jsonArray.add(jsonBuilder);
                writeJsonToFile(jsonArray, outputDir);
                rowNumber++;
            }
//            System.out.println(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeJsonToFile(ArrayList<StringBuilder> jsonArray, String outputDir) {
        try (FileWriter fileWriter = new FileWriter(outputDir + ".json")) {
            fileWriter.write(String.valueOf(jsonArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void JsonToCsv(String jsonFilePath, String outputCsvPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(new JSONObject(line));
            }

            JSONArray jsonArray = new JSONArray(jsonStringBuilder.toString());
            writeCsvToFile(jsonArray, outputCsvPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeCsvToFile(JSONArray jsonArray, String outputCsvPath) {
        try (FileWriter fileWriter = new FileWriter(outputCsvPath)) {
            if (jsonArray.length() == 0) {
                return;
            }
            for(int object = 0; object < jsonArray.length(); object++){

                JSONObject firstObject = jsonArray.getJSONObject(object);
                String[] headers = JSONObject.getNames(firstObject);
                if (headers == null) {
                    return;
                }

                // Write headers
                for (int i = 0; i < headers.length; i++) {
                    fileWriter.append(headers[i]);
                    if (i < headers.length - 1) {
                        fileWriter.append(",");
                    }
                }
                fileWriter.append("\n");

                // Write data
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    for (int j = 0; j < headers.length; j++) {
                        fileWriter.append(jsonObject.getString(headers[j]));
                        if (j < headers.length - 1) {
                            fileWriter.append(",");
                        }
                    }
                    fileWriter.append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

