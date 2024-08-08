package stepDefinations;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import stepDefinations.ConfigLoader;
import stepDefinations.FileLoader;

public class APICommonSteps {

    private RequestSpecification request;
    private Response response;
    private Properties configProperties;
    private String taxYear;

    @Given("Set the base url as {string}")
    public void set_base_url(String baseUrlKey) {
        configProperties = ConfigLoader.loadConfigProperties();
        String baseUrl = configProperties.getProperty(baseUrlKey);
        request = given().baseUri(baseUrl);
    }

    @Given("Set the endpoint as {string}")
    public void set_endpoint(String endpointKey) {
        String endpoint = configProperties.getProperty(endpointKey);
        request = request.basePath(endpoint);
    }

    @Given("Set the query parameter {string}")
    public void set_query_parameter(String paramKey) {
        String paramValue = configProperties.getProperty(paramKey);
        request = request.queryParam(paramKey, paramValue);
    }

    @Given("Set the path parameter {string}")
    public void set_path_parameter(String pathParamKey) {
        String pathParamValue = configProperties.getProperty(pathParamKey);
        request = request.pathParam(pathParamKey, pathParamValue);
    }

    @Given("Set the header as {string}")
    public void set_header(String headerKey) {
        String headerValue = configProperties.getProperty(headerKey);
        request = request.header(headerKey, headerValue);
    }

    @Given("Set the token as {string}")
    public void set_token(String tokenKey) {
        String token = configProperties.getProperty(tokenKey);
        request = request.header("Authorization", "Bearer " + token);
    }

    @Given("Set the query parameter {string} to {string}")
    public void set_query_parameter_to(String param, String value) {
        if (value.equals("null")) {
            request.queryParam(param, (String) null);
        } else {
            request.queryParam(param, value);
        }
        if (param.equals("taxYear")) {
            taxYear = value;
        }
    }

    @When("I send a GET request to the API")
    public void i_send_a_get_request_to_the_api() {
        response = request.get();
    }

    @When("I send a POST request to the API")
    public void i_send_a_post_request_to_the_api() {
        response = request.post();
    }

    @When("I send a PUT request to the API")
    public void i_send_a_put_request_to_the_api() {
        response = request.put();
    }

    @When("I send a DELETE request to the API")
    public void i_send_a_delete_request_to_the_api() {
        response = request.delete();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should match the schema from {string}")
    public void the_response_should_match_the_schema_from(String schemaPath) {
        String schema = FileLoader.loadFileAsString(schemaPath);
        response.then().body(matchesJsonSchema(schema));
    }

    @Then("the response should be saved in {string}")
    public void the_response_should_be_saved_in(String responsePath) {
        responsePath = responsePath.replace("<taxYear>", taxYear);
        FileLoader.saveStringToFile(responsePath, response.getBody().asString());
    }

    @Then("the response body should be empty")
    public void the_response_body_should_be_empty() {
        Assert.assertTrue(response.getBody().asString().isEmpty());
    }
}
