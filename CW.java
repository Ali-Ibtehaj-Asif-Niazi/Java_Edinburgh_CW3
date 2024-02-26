public class CW {
	public static void main(String[] args) {
		AirportCheckIn ac = new AirportCheckIn();
		ac.loadBookingsFromFile();
		ac.loadFlightsFromFile();
	}
}