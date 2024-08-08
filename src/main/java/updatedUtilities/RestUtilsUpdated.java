package updatedUtilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * Utility class for performing REST API requests using RestAssured library.
 */
public class RestUtilsUpdated {

    /*-----------------------------REQUEST SPECIFICATIONS--------------------------------*/

    /**
     * Builds a request specification for a POST request.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification postRequestSpecification(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a POST request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification postRequestSpecificationWithQueryParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .queryParams(queryParameters)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a POST request with path parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param pathParameters the path parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification postRequestSpecificationWithPathParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> pathParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a GET request.
     *
     * @param baseUrl  the base URL of the API
     * @param basePath the base path of the API
     * @param headers  the headers for the request
     * @return the request specification
     */
    private static RequestSpecification getRequestSpecification(String baseUrl, String basePath, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers);
    }

    /**
     * Builds a request specification for a GET request with path parameters.
     *
     * @param baseUrl       the base URL of the API
     * @param basePath      the base path of the API
     * @param pathParameters the path parameters for the request
     * @param headers       the headers for the request
     * @return the request specification
     */
    private static RequestSpecification getRequestSpecificationWithPathParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .headers(headers);
    }

    /**
     * Builds a request specification for a GET request with query parameters.
     *
     * @param baseUrl       the base URL of the API
     * @param basePath      the base path of the API
     * @param queryParameters the query parameters for the request
     * @param headers       the headers for the request
     * @return the request specification
     */
    private static RequestSpecification getRequestSpecificationWithQueryParams(String baseUrl, String basePath, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .queryParams(queryParameters)
                .headers(headers);
    }

    /**
     * Builds a request specification for a GET request with path and query parameters.
     *
     * @param baseUrl       the base URL of the API
     * @param basePath      the base path of the API
     * @param pathParameters the path parameters for the request
     * @param queryParameters the query parameters for the request
     * @param headers       the headers for the request
     * @return the request specification
     */
    private static RequestSpecification getRequestSpecificationWithPathAndQueryParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .queryParams(queryParameters)
                .headers(headers);
    }

    /**
     * Builds a request specification for a PUT request.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification putRequestSpecification(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a PUT request with path parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param pathParameters the path parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification putRequestSpecificationWithPathParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> pathParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a PUT request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification putRequestSpecificationWithQueryParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .queryParams(queryParameters)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a DELETE request.
     *
     * @param baseUrl  the base URL of the API
     * @param basePath the base path of the API
     * @param headers  the headers for the request
     * @return the request specification
     */
    private static RequestSpecification deleteRequestSpecification(String baseUrl, String basePath, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers);
    }

    /**
     * Builds a request specification for a DELETE request with path parameters.
     *
     * @param baseUrl       the base URL of the API
     * @param basePath      the base path of the API
     * @param pathParameters the path parameters for the request
     * @param headers       the headers for the request
     * @return the request specification
     */
    private static RequestSpecification deleteRequestSpecificationWithPathParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .headers(headers);
    }

    /**
     * Builds a request specification for a DELETE request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification deleteRequestSpecificationWithQueryParams(String baseUrl, String basePath, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .queryParams(queryParameters)
                .headers(headers);
    }

    /**
     * Builds a request specification for a DELETE request with path and query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param pathParameters the path parameters for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification deleteRequestSpecificationWithPathAndQueryParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .queryParams(queryParameters)
                .headers(headers);
    }

    /**
     * Builds a request specification for a PATCH request.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification patchRequestSpecification(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a PATCH request with path parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param pathParameters the path parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification patchRequestSpecificationWithPathParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> pathParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .pathParams(pathParameters)
                .headers(headers)
                .body(requestPayload);
    }

    /**
     * Builds a request specification for a PATCH request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the request specification
     */
    private static RequestSpecification patchRequestSpecificationWithQueryParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> queryParameters, Map<String, String> headers) {
        return RestAssured.given().log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .queryParams(queryParameters)
                .headers(headers)
                .body(requestPayload);
    }

    /*-----------------------------METHODS PERFORMING REQUESTS--------------------------------*/

    /**
     * Performs a POST request.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPost(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = postRequestSpecification(baseUrl, basePath, requestPayload, headers);
        return requestSpecification.post().then().log().all().extract().response();
    }

    /**
     * Performs a POST request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPostWithQueryParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = postRequestSpecificationWithQueryParams(baseUrl, basePath, requestPayload, queryParameters, headers);
        return requestSpecification.post().then().log().all().extract().response();
    }

    /**
     * Performs a POST request with path parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param pathParameters the path parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPostWithPathParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> pathParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = postRequestSpecificationWithPathParams(baseUrl, basePath, requestPayload, pathParameters, headers);
        return requestSpecification.post().then().log().all().extract().response();
    }

    /**
     * Performs a GET request.
     *
     * @param baseUrl  the base URL of the API
     * @param basePath the base path of the API
     * @param headers  the headers for the request
     * @return the response of the request
     */
    public static Response performGet(String baseUrl, String basePath, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(baseUrl, basePath, headers);
        return requestSpecification.get().then().log().all().extract().response();
    }

    /**
     * Performs a GET request with path parameters.
     *
     * @param baseUrl       the base URL of the API
     * @param basePath      the base path of the API
     * @param pathParameters the path parameters for the request
     * @param headers       the headers for the request
     * @return the response of the request
     */
    public static Response performGetWithPathParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecificationWithPathParams(baseUrl, basePath, pathParameters, headers);
        return requestSpecification.get().then().log().all().extract().response();
    }

    /**
     * Performs a GET request with query parameters.
     *
     * @param baseUrl       the base URL of the API
     * @param basePath      the base path of the API
     * @param queryParameters the query parameters for the request
     * @param headers       the headers for the request
     * @return the response of the request
     */
    public static Response performGetWithQueryParams(String baseUrl, String basePath, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecificationWithQueryParams(baseUrl, basePath, queryParameters, headers);
        return requestSpecification.get().then().log().all().extract().response();
    }

    /**
     * Performs a GET request with path and query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param pathParameters the path parameters for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performGetWithPathAndQueryParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecificationWithPathAndQueryParams(baseUrl, basePath, pathParameters, queryParameters, headers);
        return requestSpecification.get().then().log().all().extract().response();
    }

    /**
     * Performs a PUT request.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPut(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = putRequestSpecification(baseUrl, basePath, requestPayload, headers);
        return requestSpecification.put().then().log().all().extract().response();
    }

    /**
     * Performs a PUT request with path parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param pathParameters the path parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPutWithPathParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> pathParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = putRequestSpecificationWithPathParams(baseUrl, basePath, requestPayload, pathParameters, headers);
        return requestSpecification.put().then().log().all().extract().response();
    }

    /**
     * Performs a PUT request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPutWithQueryParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = putRequestSpecificationWithQueryParams(baseUrl, basePath, requestPayload, queryParameters, headers);
        return requestSpecification.put().then().log().all().extract().response();
    }

    /**
     * Performs a DELETE request.
     *
     * @param baseUrl  the base URL of the API
     * @param basePath the base path of the API
     * @param headers  the headers for the request
     * @return the response of the request
     */
    public static Response performDelete(String baseUrl, String basePath, Map<String, String> headers) {
        RequestSpecification requestSpecification = deleteRequestSpecification(baseUrl, basePath, headers);
        return requestSpecification.delete().then().log().all().extract().response();
    }

    /**
     * Performs a DELETE request with path parameters.
     *
     * @param baseUrl       the base URL of the API
     * @param basePath      the base path of the API
     * @param pathParameters the path parameters for the request
     * @param headers       the headers for the request
     * @return the response of the request
     */
    public static Response performDeleteWithPathParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = deleteRequestSpecificationWithPathParams(baseUrl, basePath, pathParameters, headers);
        return requestSpecification.delete().then().log().all().extract().response();
    }

    /**
     * Performs a DELETE request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performDeleteWithQueryParams(String baseUrl, String basePath, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = deleteRequestSpecificationWithQueryParams(baseUrl, basePath, queryParameters, headers);
        return requestSpecification.delete().then().log().all().extract().response();
    }

    /**
     * Performs a DELETE request with path and query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param pathParameters the path parameters for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performDeleteWithPathAndQueryParams(String baseUrl, String basePath, Map<String, String> pathParameters, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = deleteRequestSpecificationWithPathAndQueryParams(baseUrl, basePath, pathParameters, queryParameters, headers);
        return requestSpecification.delete().then().log().all().extract().response();
    }

    /**
     * Performs a PATCH request.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPatch(String baseUrl, String basePath, Object requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = patchRequestSpecification(baseUrl, basePath, requestPayload, headers);
        return requestSpecification.patch().then().log().all().extract().response();
    }

    /**
     * Performs a PATCH request with path parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param pathParameters the path parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPatchWithPathParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> pathParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = patchRequestSpecificationWithPathParams(baseUrl, basePath, requestPayload, pathParameters, headers);
        return requestSpecification.patch().then().log().all().extract().response();
    }

    /**
     * Performs a PATCH request with query parameters.
     *
     * @param baseUrl        the base URL of the API
     * @param basePath       the base path of the API
     * @param requestPayload the payload for the request
     * @param queryParameters the query parameters for the request
     * @param headers        the headers for the request
     * @return the response of the request
     */
    public static Response performPatchWithQueryParams(String baseUrl, String basePath, Object requestPayload, Map<String, String> queryParameters, Map<String, String> headers) {
        RequestSpecification requestSpecification = patchRequestSpecificationWithQueryParams(baseUrl, basePath, requestPayload, queryParameters, headers);
        return requestSpecification.patch().then().log().all().extract().response();
    }
}