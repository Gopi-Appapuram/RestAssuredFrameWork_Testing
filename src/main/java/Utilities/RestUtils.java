package Utilities;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility class for performing REST API requests using RestAssured library.
 */
public class RestUtils {

    /*-----------------------------POST REQUEST SPECIFICATION--------------------------------*/

    /**
     * Builds a request specification for POST method with a String request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a String.
     * @param headers        Request headers.
     * @return RequestSpecification object for POST method.
     */
    private static RequestSpecification postRequestSpecification(String baseUrl, String basePath, String requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                //.contentType(ContentType.JSON)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for POST method with an Object request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as an Object.
     * @param headers        Request headers.
     * @return RequestSpecification object for POST method.
     */
    private static RequestSpecification postRequestSpecification(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                //.contentType(ContentType.JSON)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for POST method with a Map request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a Map.
     * @param headers        Request headers.
     * @return RequestSpecification object for POST method.
     */
    private static RequestSpecification postRequestSpecification(String baseUrl, String basePath, Map<String, Object> requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                //.contentType(ContentType.JSON)
                .body(requestPayload);
    }



    /*-----------------------------GET REQUEST SPECIFICATION--------------------------------*/


    /**
     * Builds a request specification for GET method without parameters.
     *
     * @param baseUrl  Base URL of the API.
     * @param basePath API basePath.
     * @param headers  Request headers.
     * @return RequestSpecification object for GET method.
     */
    private static RequestSpecification getRequestSpecification(String baseUrl, String basePath, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers);
        //.contentType(ContentType.JSON)
    }

    /**
     * Builds a request specification for GET method with path parameters.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param pathParameters Path parameters for the basePath.
     * @param headers        Request headers.
     * @return RequestSpecification object for GET method.
     */
    private static RequestSpecification getRequestSpecification(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .headers(headers);
        //.contentType(ContentType.JSON)
    }

    /**
     * Builds a request specification for GET method with path and query parameters.
     *
     * @param baseUrl         Base URL of the API.
     * @param basePath        API basePath.
     * @param pathParameters  Path parameters for the basePath.
     * @param queryParameters Query parameters for the basePath.
     * @param headers         Request headers.
     * @return RequestSpecification object for GET method.
     */
    private static RequestSpecification getRequestSpecification(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .queryParams(queryParameters)
                .headers(headers);
        //.contentType(ContentType.JSON)
    }


    /*-----------------------------PUT REQUEST SPECIFICATIONS--------------------------------*/

    /**
     * Builds a request specification for PUT method with a Map request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a Map.
     * @param headers        Request headers.
     * @return RequestSpecification object for PUT method.
     */
    private static RequestSpecification putRequestSpecification(String baseUrl, String basePath, Map<String, Object> requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for PUT method with an Object request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as an Object.
     * @param headers        Request headers.
     * @return RequestSpecification object for PUT method.
     */
    private static RequestSpecification putRequestSpecification(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for PUT method with a String request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a String.
     * @param headers        Request headers.
     * @return RequestSpecification object for PUT method.
     */
    private static RequestSpecification putRequestSpecification(String baseUrl, String basePath, String requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .body(requestPayload);
    }


    /*----------------------------------POST METHODS-----------------------------------*/


    /**
     * Performs a POST request with a Map request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a Map.
     * @param headers        Request headers.
     * @return Response object.
     */
    public static Response performPost(String baseUrl, String basePath, Map<String, Object> requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = postRequestSpecification(baseUrl, basePath, requestPayload, headers);
        return requestSpecification.post().then().log().all().extract().response();
    }


    /**
     * Performs a POST request with an Object request payload.
     *
     * @param baseUrl              Base URL of the API.
     * @param basePath             API basePath.
     * @param requestPayloadAsPojo Request payload as an Object.
     * @param headers              Request headers.
     * @return Response object.
     */
    public static Response performPost(String baseUrl, String basePath, Object requestPayloadAsPojo, Map<String, String> headers) {
        RequestSpecification requestSpecification = postRequestSpecification(baseUrl, basePath, requestPayloadAsPojo, headers);
        return requestSpecification.post().then().log().all().extract().response();
    }

    /**
     * Performs a POST request with a String request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a String.
     * @param headers        Request headers.
     * @return Response object.
     */
    public static Response performPost(String baseUrl, String basePath, String requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = postRequestSpecification(baseUrl, basePath, requestPayload, headers);
        return requestSpecification.post().then().log().all().extract().response();
    }

    /*-----------------------------GET METHODS----------------------------*/

    /**
     * Performs a GET request without parameters.
     *
     * @param baseurl  Base URL of the API.
     * @param basePath API basePath.
     * @param headers  Request headers.
     * @return Response object.
     */
    public static Response performGet(String baseurl, String basePath, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(baseurl, basePath, headers);
        Response response = requestSpecification.get().then().log().all().extract().response();
        System.out.println(response);
        return response;
    }


    /**
     * Performs a GET request with path parameters.
     *
     * @param baseurl        Base URL of the API.
     * @param basePath       API basePath.
     * @param pathParameters Path parameters for the basePath as map.
     * @param headers        Request headers.
     * @return Response object.
     */
    public static Response performGet(String baseurl, String basePath, Map<String, String> pathParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(baseurl, basePath, pathParameters, headers);
        Response response = requestSpecification.get().then().log().all().extract().response();
        System.out.println(response);
        return response;
    }

    /**
     * Performs a GET request with path parameters and query parameters.
     *
     * @param baseurl         Base URL of the API.
     * @param basePath        API basePath.
     * @param pathParameters  Path parameters for the basePath as map.
     * @param queryParameters Path parameters for the basePath as map.
     * @param headers         Request headers.
     * @return Response object.
     */
    public static Response performGet(String baseurl, String basePath, Map<String, String> pathParameters, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(baseurl, basePath, pathParameters, queryParameters, headers);
        Response response = requestSpecification.get().then().log().all().extract().response();
        System.out.println(response);
        return response;
    }


    /*----------------------------------PUT METHODS-----------------------------------*/

    /**
     * Performs a PUT request with a Map request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a Map.
     * @param headers        Request headers.
     * @return Response object.
     */
    public static Response performPut(String baseUrl, String basePath, Map<String, Object> requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = putRequestSpecification(baseUrl, basePath, requestPayload, headers);
        return requestSpecification.put().then().log().all().extract().response();
    }

    /**
     * Performs a PUT request with an Object request payload.
     *
     * @param baseUrl              Base URL of the API.
     * @param basePath             API basePath.
     * @param requestPayloadAsPojo Request payload as an Object.
     * @param headers              Request headers.
     * @return Response object.
     */
    public static Response performPut(String baseUrl, String basePath, Object requestPayloadAsPojo, Map<String, String> headers) {
        RequestSpecification requestSpecification = putRequestSpecification(baseUrl, basePath, requestPayloadAsPojo, headers);
        return requestSpecification.put().then().log().all().extract().response();
    }

    /**
     * Performs a PUT request with a String request payload.
     *
     * @param baseUrl        Base URL of the API.
     * @param basePath       API basePath.
     * @param requestPayload Request payload as a String.
     * @param headers        Request headers.
     * @return Response object.
     */
    public static Response performPut(String baseUrl, String basePath, String requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = putRequestSpecification(baseUrl, basePath, requestPayload, headers);
        return requestSpecification.put().then().log().all().extract().response();
    }

}
