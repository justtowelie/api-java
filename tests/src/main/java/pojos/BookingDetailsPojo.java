package pojos;

import lombok.Data;

@Data
public class BookingDetailsPojo {
    private String firstName;
    private String lastName;
    private Integer totalPrice;
    private boolean depositPaid;
    private BookingDates bookingDates;
    private String additionalNeeds;

    public BookingDetailsPojo()
    {

    }

    public BookingDetailsPojo(boolean depositPaid, String additionalNeeds)
    {
        this.firstName = "Test";
        this.lastName = "Automation";
        this.totalPrice = 50;
        this.depositPaid = depositPaid;
        this.bookingDates = new BookingDates();
        this.additionalNeeds = additionalNeeds;
    }

}
