package utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

public class RestClient {


    public void setUp(String baseURI)
    {
        RestAssured.baseURI = baseURI;

        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter());
    }

    public Response getRequest(String endpoint)
    {
        // Perform GET request to /booking endpoint
        return RestAssured.given()
                .get(endpoint);
    }

}
