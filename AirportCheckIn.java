import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AirportCheckIn {
	public File bookingsFile;
	public File flightsFile;
	public HashMap<String, Booking> bookings;
	public HashMap<String, Flight> flights;

	public AirportCheckIn() {
		bookings = new HashMap<>();
		flights = new HashMap<>();
    }

	public void loadBookingsFromFile() {
		String line = "";  
		try {
			BufferedReader br = new BufferedReader(new FileReader("bookings.txt"));  
			while ((line = br.readLine()) != null)
			{  
				String[] fileLine = line.split(",");
				if (!isValidBookingReference(fileLine[0])) {
                    System.out.println("Invalid booking reference: " + fileLine[0]);
                    System.out.println("Please correct the file and load again.");
                    return; 
                }
				boolean checkedIn = fileLine[3].equals("true");
				Booking newBooking = new Booking(fileLine[0],fileLine[1],fileLine[2],checkedIn);
				bookings.put(fileLine[0], newBooking);
			}
			for (HashMap.Entry<String, Booking> entry : bookings.entrySet()) {
				String key = entry.getKey();
				Booking booking = entry.getValue();
				String bookingInfo = "Booking ID: " + key +
									", Passenger Name: " + booking.getPassengerName() +
									", Flight Code: " + booking.getFlightCode() +
									", Checked In: " + booking.isCheckedIn();
				System.out.println(bookingInfo);
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean isValidBookingReference(String bookingRef) {
        Pattern pattern = Pattern.compile("^[A-Z]{3}\\d{3}$");
        Matcher matcher = pattern.matcher(bookingRef);
        return matcher.matches();
    }
	
	public void loadFlightsFromFile() {
		String line = "";  
		try {
			BufferedReader br = new BufferedReader(new FileReader("flights.txt"));  
			while ((line = br.readLine()) != null)
			{  
				String[] fileLine = line.split(",");
				Flight newFlight = new Flight(fileLine[0],fileLine[1],fileLine[2],Integer.parseInt(fileLine[3]),Double.parseDouble(fileLine[4]),Double.parseDouble(fileLine[5]));
				flights.put(fileLine[0], newFlight);
			}
			for (HashMap.Entry<String, Flight> entry : flights.entrySet()) {
				String key = entry.getKey();
				Flight flight = entry.getValue();
				String flightInfo = "FLight ID: " + key +
									", Des: " + flight.getDestinationAirport() +
									", carrie: " + flight.getCarrier() +
									", Capacity: " + flight.getCapacity();
				System.out.println(flightInfo);
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void displayCheckInKiosk() {
	}
	public void generateReport() {
	}
}