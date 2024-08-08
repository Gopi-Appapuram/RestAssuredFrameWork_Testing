Feature: Common API Validation Steps

    Background:
        Given Set the base url as "base_URL"
        And Set the endpoint as "endpoint"
        And Set the query parameter "api-version"
        And Set the path parameter "path"
        And Set the header as "header"
        And Set the token as "token"

    Scenario Outline: Validate API response
        Given Set the query parameter "taxYear" to "<taxYear>"
        When I send a GET request to the API
        Then the response status code should be <statusCode>
        And the response should match the schema from "response_schema.json"
        And the response should be saved in "response_<taxYear>.json"

        Examples:
            | taxYear | statusCode |
            | 2023    | 200        |
            | 2010    | 204        |
            | null    | 400        |
