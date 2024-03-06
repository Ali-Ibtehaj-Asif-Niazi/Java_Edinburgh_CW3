package src.test.java;
import src.main.java.Booking;*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookingTest {

    @Test
    public void testBookingInitialization() {
        Booking btest = new Booking("HWU123", "Heriot", "F11", false);

        assertEquals("REF123", btest.getBookingRefCode());
        assertEquals("John Doe", btest.getPassengerName());
        assertEquals("FL123", btest.getFlightCode());
        assertFalse(btest.isCheckedIn());
    }

    @Test
    public void testSetCheckedIn() {
        Booking btest2 = new Booking("HWU123", "Heriot", "F11", false);

        assertFalse(btest2.isCheckedIn());
        btest2.setCheckedIn(true);
        assertTrue(btest2.isCheckedIn());
    }
}
