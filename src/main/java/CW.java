public class CW {
    // Main method where program execution starts
    public static void main(String[] args) {
    	
        AirportGUI gui = new AirportGUI();

        // Creating an instance of AirportCheckIn class
        AirportSimulation simulation = new AirportSimulation(gui);
        
        // Loading bookings from file
        simulation.loadBookingsFromFile();
        
        // Loading flights from file
        simulation.loadFlightsFromFile();

        // Set GUI visible
        gui.setVisible(true);
        
        // Start the simulation
        simulation.startSimulation();
        // Create an instance of AirportGUI
        
    }
}