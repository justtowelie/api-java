package pojos;

import lombok.Data;
import utils.DataGenerator;

@Data
public class BookingDates {
    private String checkin;
    private String checkout;

    public BookingDates()
    {
        DataGenerator dataGenerator = DataGenerator.getInstance();
        this.checkin = dataGenerator.getTodaysDate();
        this.checkout = dataGenerator.addDaysToTodaysDate(3);

    }
}
