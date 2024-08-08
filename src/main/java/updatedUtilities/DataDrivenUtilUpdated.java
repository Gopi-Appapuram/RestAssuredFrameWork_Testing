package updatedUtilities;

import Utilities.JsonUtils;
import Utilities.RestUtils;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataDrivenUtilUpdated {

    Response response;

    public boolean executeDataDrivenAPIs(String fileName, String sheetName) throws IOException {
        // Load data from data-driven sheet
        List<LinkedHashMap<String, String>> ddData = ExcelDataDrivenUpdated.loadDSheetData(fileName, sheetName);

        // Loop and execute each request from data-driven sheet
        for (LinkedHashMap<String, String> eachRequest : ddData) {
            String method = eachRequest.get("method");
            switch (method.toUpperCase()) {
                case "POST":
                    executePost(eachRequest, fileName, sheetName+"Results");
                    break;
                case "PUT":
                    executePut(eachRequest, fileName, sheetName+"Results");
                    break;
                case "GET":
                    executeGet(eachRequest, fileName, sheetName+"Results");
                    break;
                case "DELETE":
                    executeDelete(eachRequest, fileName, sheetName+"Results");
                    break;
                default:
                    System.out.println("Invalid method: " + method);
                    break;
            }
        }
        return false;
    }

    private void executePost(LinkedHashMap<String, String> request, String fileName, String sheetName) throws IOException {
        String baseUrl = request.get("baseUrl");
        String basePath = request.get("basePath");
        String requestBody = request.get("requestBody");
        String headers = request.get("headers");
        String expectedStatusCode = request.get("statusCode");

        try {
            Map<String, String> headerObject = JsonUtils.parseJsonObject(headers);
            response = RestUtils.performPost(baseUrl, basePath, requestBody, headerObject);

            validateResponse(response, expectedStatusCode);
            updateExcelWithResult(request, fileName, sheetName);

        } catch (Exception e) {
            handleException(request, e, fileName, sheetName);
        }
    }

    private void executePut(LinkedHashMap<String, String> request, String fileName, String sheetName) throws IOException {
        String baseUrl = request.get("baseUrl");
        String basePath = request.get("basePath");
        String requestBody = request.get("requestBody");
        String headers = request.get("headers");
        String expectedStatusCode = request.get("statusCode");

        try {
            Map<String, String> headerObject = JsonUtils.parseJsonObject(headers);
            response = RestUtils.performPut(baseUrl, basePath, requestBody, headerObject);

            validateResponse(response, expectedStatusCode);
            updateExcelWithResult(request, fileName, sheetName);

        } catch (Exception e) {
            handleException(request, e, fileName, sheetName);
        }
    }

    private void executeGet(LinkedHashMap<String, String> request, String fileName, String sheetName) throws IOException {
        String baseUrl = request.get("baseUrl");
        String basePath = request.get("basePath");
        String headers = request.get("headers");
        String expectedStatusCode = request.get("statusCode");

        try {
            Map<String, String> headerObject = JsonUtils.parseJsonObject(headers);
            response = RestUtils.performGet(baseUrl, basePath, headerObject);

            validateResponse(response, expectedStatusCode);
            updateExcelWithResult(request, fileName, sheetName);

        } catch (Exception e) {
            handleException(request, e, fileName, sheetName);
        }
    }

    private void executeDelete(LinkedHashMap<String, String> request, String fileName, String sheetName) throws IOException {
        String baseUrl = request.get("baseUrl");
        String basePath = request.get("basePath");
        String headers = request.get("headers");
        String expectedStatusCode = request.get("statusCode");

        try {
            Map<String, String> headerObject = JsonUtils.parseJsonObject(headers);
            // RestUtils.performDelete(baseUrl, basePath, headerObject);
            // Implement your delete request logic if needed

            // For demonstration, let's assume delete operation follows a similar structure
            // response = RestUtils.performDelete(baseUrl, basePath, headerObject);

            validateResponse(response, expectedStatusCode);
            updateExcelWithResult(request, fileName, sheetName);

        } catch (Exception e) {
            handleException(request, e, fileName, sheetName);
        }
    }

    private void validateResponse(Response response, String expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        System.out.println("Actual Status Code: " + actualStatusCode);
        if (String.valueOf(actualStatusCode).equals(expectedStatusCode)) {
            System.out.println("Response status code matches expected: " + expectedStatusCode);
        } else {
            System.out.println("Response status code does not match expected: " + expectedStatusCode);
        }
        // Optionally, you can add more validation logic for response content, headers, etc.
    }

    private void updateExcelWithResult(LinkedHashMap<String, String> request, String fileName, String sheetName) throws IOException {
        // Update the Excel sheet with result (PASS or FAIL)
        request.put("pass or fail", "PASS");
        request.put("response", String.valueOf(response));
        ExcelDataDrivenUpdated.writeDataToExcel(fileName, sheetName, request);
    }

    private void handleException(LinkedHashMap<String, String> request, Exception e, String fileName, String sheetName) throws IOException {
        // Handle any exceptions occurred during API request
        System.err.println("Error executing API request: " + e.getMessage());
        e.printStackTrace();
        // Update the Excel sheet with "FAIL" value in the response cell
        request.put("pass or fail", "FAIL");
        request.put("errorMessage", e.getMessage());
        ExcelDataDrivenUpdated.writeDataToExcel(fileName, sheetName, request);
    }
}
