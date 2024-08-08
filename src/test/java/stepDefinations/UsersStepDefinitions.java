package stepDefinations;

import Users.UsersApis;
import updatedUtilities.ExcelDataDrivenUpdated;
import Utilities.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import pojos.CreateUsersPojo;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UsersStepDefinitions {

    private UsersApis usersApis;
    private Response response;
    CreateUsersPojo CreateUserPayload;

    @Given("I have users Apis")
    public void iHaveUsersApis() {
        usersApis = new UsersApis();
    }

    @Given("I have user data")
    public void iHaveUserData() throws IOException {
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelDataDrivenUpdated.loadDSheetData("src/test/resources/TestData/RestAssured.xlsx", "Sheet2");

        HashMap<String, String> anyRow = excelDataAsListOfMap.get(0);

        Object[] keys = anyRow.keySet().toArray();

        for (Map<String, String> eachRequest : excelDataAsListOfMap) {
            String baseUrl = "";
            String endpoint = "";
            String pathParameter = "";
            String headers = "";
            Map<String, String> headerObject = null;
            String method = "";
            String requestBody = "";
            String statusCode = "";

            for (int key = 0; key < keys.length; key++) {
                String keyName = String.valueOf(keys[key]);

                switch (keyName.toLowerCase()) {
                    case "baseurl":
                        baseUrl = eachRequest.get(keyName);
                        break;
                    case "endpoint":
                        endpoint = eachRequest.get(keyName);
                        break;
                    case "pathparameter":
                        pathParameter = eachRequest.get(keyName);
                        break;
                    case "headers":
                        headers = eachRequest.get(keyName);
                        headerObject = JsonUtils.parseJsonObject(headers);
                        break;
                    case "method":
                        method = eachRequest.get(keyName);
                        break;
                    case "request":
                        requestBody = eachRequest.get(keyName);
                        break;
                    case "status code":
                        statusCode = eachRequest.get(keyName);
                        break;
                }
            }
            System.out.println("baseUrl: " + baseUrl + "\nEndPoint: " + endpoint + "\npath" + pathParameter + "\n" + headerObject + "\nRequest: " + requestBody);
            iCreateANewUser(baseUrl, endpoint, requestBody, headerObject); // Call the create user step with each payload
            theUserShouldBeCreatedSuccessfully(); // Verify the user creation for each payload

        }


/*        while (iterator.hasNext()) {
            CreateUserPayload = iterator.next();
            iCreateANewUser(); // Call the create user step with each payload
            theUserShouldBeCreatedSuccessfully(); // Verify the user creation for each payload
        }*/

        //CreateUserPayload = new Payload().getCreateUserPayloadFromExcel();
        //CreateUserPayload = Payload.getCreateUserPayloadFromPojo();
        // CreateUserPayload = new CreateUsersPojo().toBuilder().name("Gopi_Appapuram").given_name("Gopi").build();

    }

    @When("I create a new user")
    public void iCreateANewUser(String baseUrl, String endPoint, Object createUserPayload, Map<String, String> headers) throws IOException {
        //response = usersApis.performAction(CreateUserPayload);
        response = usersApis.createUser(baseUrl, endPoint, createUserPayload, headers);
    }

    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() throws IOException {
        Assert.assertEquals(response.statusCode(), 201);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateUsersPojo createUserResponse = objectMapper.readValue(response.getBody().asString(), CreateUsersPojo.class);
        Assert.assertEquals(createUserResponse, CreateUserPayload);
    }


    @When("I Get all the users")
    public void iGetAllTheUsers() throws IOException {
        //response = usersApis.performAction(CreateUserPayload);
        response = usersApis.getAllUsers();
    }

}
