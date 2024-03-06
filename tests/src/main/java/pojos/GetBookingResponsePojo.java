package pojos;

import lombok.Data;

@Data
public class GetBookingResponsePojo {
    private String firstName;
    private String lastName;
    private Integer totalPrice;
    private boolean depositPaid;
    private BookingDates bookingDates;
    private String additionalNeeds;

    public GetBookingResponsePojo()
    {

    }

}
