public class CW {
    // Main method where program execution starts
    public static void main(String[] args) {
        // Creating an instance of AirportCheckIn class
        AirportCheckIn ac = new AirportCheckIn();
        
        // Loading bookings from file
        ac.loadBookingsFromFile();
        
        // Loading flights from file
        ac.loadFlightsFromFile();
        
        // Displaying check-in kiosk GUI
        ac.displayCheckInKiosk();
    }
}
