package test;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Tests {

    @BeforeEach
    void setup() {
        // Set base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter());
    }

    @Test
    @Tag("debug")
    @DisplayName("Get booking")
    void sendGetRequest() {
        // Perform GET request to /booking endpoint
        Response response = RestAssured.given()
                .get("/booking");

        // Assert status code
        assertEquals(200, response.getStatusCode(), "Status code is not as expected");
    }
}
