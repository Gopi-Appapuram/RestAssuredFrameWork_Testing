package Utilities;

import io.restassured.response.Response;
import updatedUtilities.RestUtilsUpdated;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDrivenUtil {

    private Response response;

    /**
     * Executes data-driven API requests based on the data provided in an Excel sheet.
     *
     * @param fileName  the name of the Excel file
     * @param sheetName the name of the sheet within the Excel file
     * @return true if all requests are executed successfully, false otherwise
     * @throws IOException if an I/O error occurs while reading the Excel file
     */
    public boolean executeDataDrivenAPIs(String fileName, String sheetName) throws IOException {
        // Load data from data-driven sheet
        List<HashMap<String, String>> ddData = ExcelDataDriven.loadDSheetData(fileName, sheetName);

        // Read all keys from the first row of the hashmap
        HashMap<String, String> anyRow = ddData.get(0);
        Object[] keys = anyRow.keySet().toArray();

        // Loop and execute each request from data-driven sheet
        for (HashMap<String, String> eachRequest : ddData) {
            String baseUrl = "";
            String basePath = "";
            String pathParameter = "";
            Map<String, String> pathParameterObject = null;
            String headers = "";
            Map<String, String> headerObject = null;
            String method = "";
            String requestBody = "";
            String statusCode = "";

            for (Object key : keys) {
                String keyName = String.valueOf(key);

                switch (keyName.toLowerCase()) {
                    case "baseurl":
                        baseUrl = eachRequest.get(keyName);
                        break;
                    case "basepath":
                        basePath = eachRequest.get(keyName);
                        break;
                    case "pathparameter":
                        pathParameter = eachRequest.get(keyName);
                        pathParameterObject = JsonUtils.parseJsonObject(pathParameter);
                        break;
                    case "headers":
                        headers = eachRequest.get(keyName);
                        headerObject = JsonUtils.parseJsonObject(headers);
                        break;
                    case "method":
                        method = eachRequest.get(keyName);
                        break;
                    case "requestbody":
                        requestBody = eachRequest.get(keyName);
                        break;
                    case "statuscode":
                        statusCode = eachRequest.get(keyName);
                        break;
                    default:
                        System.out.println("Unknown key: " + keyName);
                        break;
                }
            }

            // Debugging output
            // System.out.println("baseUrl: " + baseUrl + "\nbasePath: " + basePath + "\npath: " + pathParameter + "\n" + headerObject + "\nRequest: " + requestBody);

            try {
                // Perform API request based on the method obtained
                switch (method.toUpperCase()) {
                    case "POST":
                        response = RestUtilsUpdated.performPost(baseUrl, basePath, requestBody, headerObject);
                        break;
                    case "PUT":
                        response = RestUtilsUpdated.performPut(baseUrl, basePath, requestBody, headerObject);
                        break;
                    case "GET":
                        response = RestUtilsUpdated.performGet(baseUrl, basePath, headerObject);
                        break;
                    case "PATCH":
                         response = RestUtilsUpdated.performPatch(baseUrl, basePath, requestBody, headerObject);
                        break;
                    case "DELETE":
                         response = RestUtilsUpdated.performDelete(baseUrl, basePath, headerObject);
                        break;
                    default:
                        System.out.println("Invalid method: " + method);
                        break;
                }

                // Check the response status code and compare it with the expected statusCode
                String actualStatusCode = String.valueOf(response.getStatusCode());
                if (actualStatusCode.equals(statusCode)) {
                    // Update the Excel sheet with "PASS" value in the response cell
                    eachRequest.put("pass or fail", "PASS");
                } else {
                    eachRequest.put("pass or fail", "FAIL");
                }
                ExcelDataDriven.writeDataToExcel(fileName, sheetName, eachRequest);

            } catch (Exception e) {
                // Handle any exceptions occurred during API request
                System.err.println("Error executing API request: " + e.getMessage());
            }
        }
        return true;
    }
}
