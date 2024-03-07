package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.BookingIdPojo;
import pojos.XmlBookingPojo;
import utils.RestClient;

public class XmlTests {
    RestClient restClient = new RestClient();
    private BookingIdPojo bookingIdPojo;


    @BeforeEach
    void setup() {
        bookingIdPojo = new BookingIdPojo();
        restClient.setUp("https://restful-booker.herokuapp.com");
    }

    @Test
    @DisplayName("create a booking with xml")
    void createBooking()
    {
        XmlBookingPojo xmlBookingPojo = new XmlBookingPojo();
        Response response = restClient.postRequest(xmlBookingPojo, "/booking", ContentType.XML);
        restClient.verifySuccessfulStatus(response);
    }


}
