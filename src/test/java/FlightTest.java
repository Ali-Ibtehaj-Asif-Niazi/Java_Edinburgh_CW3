import org.junit.Test;
import static org.junit.Assert.*;

public class FlightTest {

    @Test
    public void testConstructorValidFlightCode() {
        Flight flight = new Flight("F12", "EDN", "PA", 100, 50.0, 100.0);
        assertEquals("F12", flight.getFlightCode());
        assertEquals("EDN", flight.getDestinationAirport());
        assertEquals("PA", flight.getCarrier());
        assertEquals(100, flight.getCapacity());
        assertEquals(50.0, flight.getMaxBaggageWeight(), 0.0);
        assertEquals(100.0, flight.getMaxBaggageVolume(), 0.0);
    }

    @Test
    public void testConstructorInvalidFlightCode() {
        // Attempt to create a Flight object with an invalid flight code
        Flight flight = new Flight("XYZ", "EDN", "PA", 100, 50.0, 100.0);
        assertNull(flight.getFlightCode());
        assertNull(flight.getDestinationAirport());
        assertNull(flight.getCarrier());
        assertEquals(-1,flight.getCapacity());
        assertEquals(-1.0,flight.getMaxBaggageWeight(), 0.0);
        assertEquals(-1.0,flight.getMaxBaggageVolume(), 0.0);
    }

    @Test
    public void testGetFlightCode() {
        Flight flight = new Flight("F12", "EDN", "PA", 100, 50.0, 100.0);
        assertEquals("F12", flight.getFlightCode());
    }

    @Test
    public void testGetDestinationAirport() {
        Flight flight = new Flight("F12", "EDN", "PA", 100, 50.0, 100.0);
        assertEquals("EDN", flight.getDestinationAirport());
    }

    @Test
    public void testGetCarrier() {
        Flight flight = new Flight("F12", "EDN", "PA", 100, 50.0, 100.0);
        assertEquals("PA", flight.getCarrier());
    }

    @Test
    public void testGetCapacity() {
        Flight flight = new Flight("F12", "EDN", "PA", 100, 50.0, 100.0);
        assertEquals(100, flight.getCapacity());
    }

    @Test
    public void testGetMaxBaggageWeight() {
        Flight flight = new Flight("F12", "EDN", "PA", 100, 50.0, 100.0);
        assertEquals(50.0, flight.getMaxBaggageWeight(), 0.0);
    }

    @Test
    public void testGetMaxBaggageVolume() {
        Flight flight = new Flight("F12", "EDN", "PA", 100, 50.0, 100.0);
        assertEquals(100.0, flight.getMaxBaggageVolume(), 0.0);
    }
}
