import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportCheckIn {
	public File bookingsFile;
	public File flightsFile;
	public List<Booking> bookings;
	public List<Flight> flights;
	
	public void loadBookingsFromFile() {
		String line = "";  
		bookings = new ArrayList<Booking>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("bookings.txt"));  
			while ((line = br.readLine()) != null)
			{  
				String[] fileLine = line.split(",");
				boolean checkedIn = fileLine[3] == "yes";
				Booking newBooking = new Booking(fileLine[0],fileLine[1],fileLine[2],checkedIn);
				bookings.add(newBooking);
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFlightsFromFile() {
		String line = "";  
		flights = new ArrayList<Flight>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("flights.txt"));  
			while ((line = br.readLine()) != null)
			{  
				String[] fileLine = line.split(",");
				Flight newFlight = new Flight(fileLine[0],fileLine[1],Integer.parseInt(fileLine[2]),Double.parseDouble(fileLine[3]),Double.parseDouble(fileLine[3]));
				flights.add(newFlight);
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