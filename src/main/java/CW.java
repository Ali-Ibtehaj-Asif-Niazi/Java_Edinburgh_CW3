public class CW {
    // Main method where program execution starts
    public static void main(String[] args) {
        // Creating an instance of AirportCheckIn class
        AirportSimulation simulation = new AirportSimulation();
        
        // Loading bookings from file
        simulation.loadBookingsFromFile();
        
        // Loading flights from file
        simulation.loadFlightsFromFile();

        // Start the simulation
        simulation.startSimulation();

        // Create an instance of AirportGUI
        //AirportGUI gui = new AirportGUI();

        // Set GUI visible
        //gui.setVisible(true);
    }
}