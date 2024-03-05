import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
	private CheckInGUI checkInGUI;

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
				try {
					if(!isValidBookingReference(fileLine[0])) {
						throw new IncorrectRefNumException(fileLine[0]);
					}
				} 
				catch (IncorrectRefNumException e) {
					System.out.println(e);
					System.exit(1);
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
		CheckInGUI cg = new CheckInGUI(bookings);
	}

	public void generateReport(String filename) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			ArrayList<Passenger> passengers = checkInGUI.getPassengers();
			for (HashMap.Entry<String, Flight> entry : flights.entrySet()) {
				Flight flight = entry.getValue();

				int checkedInPassengers = 0;
				double totalBaggageWeight = 0.0;
				double totalBaggageVolume = 0.0;
				double totalExcessBaggageFees = 0.0;

				for (Booking booking : bookings.values()) {
					if (booking.getFlightCode().equals(flight.getFlightCode()) && booking.isCheckedIn()) {
						checkedInPassengers++;
					}
					for (Passenger passenger : passengers) {
						if (booking.getBookingRefCode().equals(passenger.getBookingRef()) && booking.getFlightCode().equals(flight.getFlightCode())) {
							totalBaggageWeight += passenger.getBaggageWeight();
							totalBaggageVolume += passenger.getBaggageVolume();
							totalExcessBaggageFees += passenger.getExcessBaggageFee();
            			}
            		}
				}



				// Check if flight capacity is exceeded
				boolean isCapacityExceeded = checkedInPassengers > flight.getCapacity();

				writer.write("Flight: " + flight.getFlightCode());
				writer.newLine();
				writer.write("Checked-in Passengers: " + checkedInPassengers);
				writer.newLine();
				writer.write("Total Baggage Weight: " + totalBaggageWeight);
				writer.newLine();
				writer.write("Total Baggage Volume: " + totalBaggageVolume);
				writer.newLine();
				writer.write("Total Excess Baggage Fees: " + totalExcessBaggageFees);
				writer.newLine();
				writer.write("Flight Capacity Exceeded: " + isCapacityExceeded);
				writer.newLine();
				writer.write("--------------------------------------");
				writer.newLine();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}