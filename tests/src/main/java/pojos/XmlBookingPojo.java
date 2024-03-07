package pojos;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "booking")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlBookingPojo {
    @XmlAttribute
    private String firstname;
    @XmlAttribute
    private String lastname;
    @XmlAttribute
    private int totalprice;
    @XmlAttribute
    private boolean depositpaid;
    @XmlElement
    private BookingDates bookingdates;
    @XmlAttribute
    private String additionalneeds;

    public XmlBookingPojo()
    {
        this.firstname = "test";
        this.lastname = "automation";
        this.totalprice = 50;
        this.depositpaid = true;
        this.bookingdates = new BookingDates();
        this.additionalneeds = "toast";
    }
}
