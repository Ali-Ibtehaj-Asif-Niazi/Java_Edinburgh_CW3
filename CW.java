public class CW {
	public static void main(String[] args) {
		AirportCheckIn ac = new AirportCheckIn();
		ac.loadBookingsFromFile();
		ac.loadFlightsFromFile();
		ac.displayCheckInKiosk();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ac.generateReport("report.txt");
        }));
	}
}