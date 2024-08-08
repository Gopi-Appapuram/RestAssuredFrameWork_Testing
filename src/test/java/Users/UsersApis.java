package Users;

import updatedUtilities.ExcelDataDrivenUpdated;
import updatedUtilities.RestUtilsUpdated;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.*;

import static Users.Payload.getHeadersFromExcel;

public class UsersApis {

    Response response;

    public Response createUser(Object createUserPayload) throws IOException {
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelDataDrivenUpdated.loadDSheetData("src/test/resources/TestData/RestAssured.xlsx", "Sheet2");
        for (Map<String, String> data : excelDataAsListOfMap) {
            String BaseUrl = data.get("baseurl");
            String Endpoint = data.get("endpoint");
            Iterator<Map<String, String>> iterator = getHeadersFromExcel("Headers");
            Map<String, String> Headers = iterator.next();
            System.out.println(Headers);

            response = RestUtilsUpdated.performPost(BaseUrl, Endpoint, createUserPayload, Headers);
        }
        return response;
    }

    public Response createUser(String baseUrl, String endPoint, Object createUserPayload, Map<String, String> headers) throws IOException {
        response = RestUtilsUpdated.performPost(baseUrl, endPoint, createUserPayload, headers);
        return response;
    }

    public Response getAllUsers() throws IOException {

        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelDataDrivenUpdated.loadDSheetData("src/test/resources/TestData/RestAssured.xlsx", "Sheet2");
        for (Map<String, String> data : excelDataAsListOfMap) {
            String BaseUrl = data.get("baseurl");
            String Endpoint = data.get("PathParameter");
            Iterator<Map<String, String>> headerList = getHeadersFromExcel("Headers");
            Map<String, String> Headers = headerList.next();
            response = RestUtilsUpdated.performGet(BaseUrl, Endpoint, Headers);
        }
        return response;
    }

}
