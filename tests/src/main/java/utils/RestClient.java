package utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.awaitility.Awaitility;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestClient {


    public void setUp(String baseURI) {
        RestAssured.baseURI = baseURI;

        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter());
    }

    public void verifySuccessfulStatus(Response response) {
        assertEquals(200, response.getStatusCode(), "Status code is not as expected");
    }

    public Response getRequest(String endpoint) {
        // Perform GET request
        return RestAssured.given()
                .get(endpoint);
    }

    public Response postRequest(Object body, String endpoint, ContentType contentType) {
        // Perform POST request
        return RestAssured.given().contentType(contentType).body(body).post(endpoint);
    }

    public Response putRequest(Object body, String endpoint) {
        // Perform PUT request
        return RestAssured.given().contentType(ContentType.JSON).accept("application/json")
                .cookie("token", "abc123").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=").body(body).put(endpoint);
    }

    public Response deleteRequest(String endpoint) {
        return RestAssured.given().contentType(ContentType.JSON).cookie("token", "abc123").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=").delete(endpoint);
    }

    // Method to recall the endpoint until assertion is fulfilled.
    public void sendGetRequestAndWaitForAssertion(String endpoint, String path, String assertion) {
        Awaitility.await().atMost(15, TimeUnit.SECONDS).pollInterval(3, TimeUnit.SECONDS).untilAsserted(() -> assertThat(
                RestAssured.given().get(endpoint).then().contentType(ContentType.JSON).extract().response().body().path(path), is(assertion)));
    }

}
