package test;

import groovy.util.logging.Slf4j;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojos.BookingDetailsPojo;
import pojos.BookingIdPojo;
import utils.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class Tests {
    RestClient restClient = new RestClient();
    private BookingIdPojo bookingIdPojo;


    @BeforeEach
    void setup() {
        bookingIdPojo = new BookingIdPojo();
        restClient.setUp("https://restful-booker.herokuapp.com");
    }

    @Test
    @Tag("debug")
    @DisplayName("Get booking")
    void getBooking() {
        // Perform GET request to /booking endpoint
        Response response = restClient.getRequest("/booking");
        restClient.verifySuccessfulStatus(response);
        int bookingId = response.jsonPath().getInt("[0].bookingid");
        bookingIdPojo.setBookingid(bookingId); // Set the booking ID
        assertNotNull(response.body());
    }

    @Test
    @Tag("debug")
    @DisplayName("Get booking by ID")
    void getBookingById() {
        getBooking(); // Ensure that getBooking() is called before getting the booking ID
        int id = bookingIdPojo.getBookingid(); // Retrieve the booking ID from BookingIdPojo
        Response response = restClient.getRequest("/booking/" + id);
        restClient.verifySuccessfulStatus(response);
        int totalPrice = response.jsonPath().getInt("totalprice");
        assertEquals(totalPrice, 111, "Total price is not as expected");
    }


    @Test
    @DisplayName("Create a booking")
    void createBooking()
    {
        BookingDetailsPojo bookingDetailsPojo = new BookingDetailsPojo(true, "breakfast");
        Response response = restClient.postRequest(bookingDetailsPojo, "/booking");
        restClient.verifySuccessfulStatus(response);
    }

    @Test
    @DisplayName("update existing booking")
    void updateBooking()
    {
        getBooking();
        int id = bookingIdPojo.getBookingid(); // Retrieve the booking ID from BookingIdPojo
        BookingDetailsPojo bookingDetailsPojo = new BookingDetailsPojo(true, "breakfast");
        Response response = restClient.putRequest(bookingDetailsPojo, "/booking/" + id);
        restClient.verifySuccessfulStatus(response);
    }

    @Test
    @DisplayName("delete a booking")
    void deleteBooking()
    {
        getBooking();
        int id = bookingIdPojo.getBookingid(); // Retrieve the booking ID from BookingIdPojo
        Response response = restClient.deleteRequest("/booking/" + id);
        assertEquals(201, response.getStatusCode(), "Status code is not as expected");
    }
}
